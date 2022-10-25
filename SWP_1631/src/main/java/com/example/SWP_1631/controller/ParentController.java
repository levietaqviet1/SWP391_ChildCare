package com.example.SWP_1631.controller;

import com.example.SWP_1631.entity.*;
import com.example.SWP_1631.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import org.modelmapper.ModelMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/parents")
public class ParentController {
    @Autowired
    private KindergartnerService kindergartnerService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private StudyRecordService studyRecordService;


    @RequestMapping("/ParentsProfile")
    public String profile(Model model, HttpSession session) {
        Account accSe = (Account) session.getAttribute("acc");
        Optional<Account> teacherFound = accountService.getAccount(accSe.getAccountId());
        teacherFound.ifPresent(teacher -> model.addAttribute("Parents", teacher));
        return "Parents/ParentProfile";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String viewSearch(HttpServletRequest request, Model model) {
        if (request.getParameter("search") == null) {
            return "redirect:/admin/";
        }
        String search = request.getParameter("search").trim();
        search = search.replaceAll("\\s\\s+", " ").trim();
        List<Account> listAcc = accountService.getListAccount();
        List<Role> listRo = roleService.getAllRole();
        model.addAttribute("Parents", listAcc);
        model.addAttribute("listR", listRo);
        return "Parents/ParentProfile";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editUser(@RequestParam("id") Integer userId, Model model) {
        Optional<Account> userEdit = accountService.getAccount(userId);
        userEdit.ifPresent(user -> model.addAttribute("Parents", user));
        List<Role> listRo = roleService.getAllRole();
        model.addAttribute("listR", listRo);
        return "Parents/parentUpdateProfile";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("id") Integer userId, HttpServletRequest res) throws Exception {
        Optional<Account> ac2 = accountService.getAccount(userId);
        Account ac = new Account();
        if (ac2.isPresent()) {
            ac.setRole(ac2.get().getRole());
            ac.setPassword(ac2.get().getPassword());
        }
        ac.setFirstName(res.getParameter("txtFirstName"));
        ac.setLastName(res.getParameter("txtLastName"));
        boolean gen = res.getParameter("gender").equals("1");
        ac.setGender(gen);
        ac.setEmail(res.getParameter("txtEmail"));
        ac.setDob(res.getParameter("dob"));
        ac.setPhoneNumber(res.getParameter("txtPhone"));
        ac.setAddress(res.getParameter("ttAddress"));
        ac.setImg(res.getParameter("txtImg"));
        ac.setAccountId(userId);
        System.out.println(ac.toString());
        accountService.update(ac);
        return "redirect:/parents/ParentsProfile";
    }

    // Dức
    @RequestMapping("/childprofile")
    public String showchild(Model model, HttpSession session, HttpServletRequest res) {
        Account accSession = (Account) session.getAttribute("acc");
        Optional<Account> acc = accountService.getAccount(accSession.getAccountId());
        acc.ifPresent(ac -> model.addAttribute("Account", ac));
        if (acc.isPresent()) {
            List<Kindergartner> listKinder = kindergartnerService.getListKinderByIdParent(acc.get().getAccountId());
            model.addAttribute("listKinder", listKinder);
            if (res.getParameter("mainchildid") != null) {
                int index = Integer.parseInt(res.getParameter("mainchildid"));
                Optional<Kindergartner> kindergartner = kindergartnerService.getKindergartnerById(index);
                kindergartner.ifPresent(user -> model.addAttribute("Kinder", user));
                StudyRecord studyRecord = studyRecordService.getStudyRecordByIdKinderId(index);
                model.addAttribute("studyRecord", studyRecord);
                model.addAttribute("mainchildid", index);
            } else {
                if (listKinder.size() > 0) {
                    model.addAttribute("Kinder", listKinder.get(0));
                    StudyRecord studyRecord = studyRecordService.getStudyRecordByIdKinderId(listKinder.get(0).getKinderId());
                    model.addAttribute("studyRecord", studyRecord);
                    model.addAttribute("mainchildid", listKinder.get(0).getKinderId());
                }
            }

        }
        return "Parents/childprofile";
    }

    @RequestMapping(value = "/editChild", method = RequestMethod.GET)
    public String editchild(@RequestParam("id") Integer userId, Model model, HttpSession session) {
        Account accSession = (Account) session.getAttribute("acc");
        Optional<Account> acc = accountService.getAccount(accSession.getAccountId());
        acc.ifPresent(ac -> model.addAttribute("Account", ac));
        if (acc.isPresent()) {
            int index = userId;
            Optional<Kindergartner> kindergartner = kindergartnerService.getKindergartnerById(index);
            kindergartner.ifPresent(user -> model.addAttribute("Kinder", user));
            StudyRecord studyRecord = studyRecordService.getStudyRecordByIdKinderId(index);
            model.addAttribute("studyRecord", studyRecord);
            model.addAttribute("mainchildid", index);
        }


        return "Parents/childupdateprofile";
    }

    @RequestMapping(value = "/updateChild", method = RequestMethod.POST)
    public String updateC(@RequestParam("id") Integer userId, HttpServletRequest res) {
        Kindergartner kindergartner = new Kindergartner();
        Optional<Kindergartner> opKin = kindergartnerService.getKindergartnerById(userId);
        if (opKin.isPresent()) {
            kindergartner = opKin.get();
        }
        kindergartner.setFirstName(res.getParameter("txtFirstName"));
        kindergartner.setLastName(res.getParameter("txtLastName"));
        boolean gen = res.getParameter("gender").equals("1");
        kindergartner.setGender(gen);
        try {
            kindergartner.setDob(res.getParameter("dob"));
        } catch (Exception e) {

        }
        kindergartnerService.update(kindergartner);
        return "redirect:/parents/childprofile";
    }

    // n.a
    @GetMapping("/child")
    public String view(Model model) {
        List<Clazz> list = clazzService.getAllClazz();
        model.addAttribute("ListClazz", list);
        return "childrenregister/childregister";
    }

    @RequestMapping(value = "/registerChild", method = RequestMethod.POST)
    public String update(HttpServletRequest res, HttpSession session) throws Exception {
        Kindergartner kindergartner = new Kindergartner();
        Account accSession = (Account) session.getAttribute("acc");
        kindergartner.setAccount(accSession);
        kindergartner.setFirstName(res.getParameter("ChildFirstName"));
        kindergartner.setLastName(res.getParameter("ChildLastName"));
        boolean gen = !res.getParameter("flexRadioDefault").equals("female");
        kindergartner.setGender(gen);
        kindergartner.setDob(res.getParameter("dob"));
        kindergartnerService.save(kindergartner);
        StudyRecord studyRecord = new StudyRecord();

        Optional<Clazz> clazz = clazzService.getById(Integer.parseInt(res.getParameter("register_classid")));
        if (clazz.isPresent()) {
            studyRecord.setClassId(clazz.get());
            studyRecord.setKinderId(kindergartner);
            studyRecord.setStudyYear(java.time.LocalDate.now().getYear());
        }
        studyRecordService.save(studyRecord);
        return "redirect:/parents/child";
    }
}

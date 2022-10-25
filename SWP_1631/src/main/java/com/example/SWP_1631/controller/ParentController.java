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
    private ClazzService clazzService ;
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
        boolean gen = !res.getParameter("gender").equals("female");
        ac.setGender(gen);
        ac.setEmail(res.getParameter("txtEmail"));
        ac.setDob(res.getParameter("dob"));
        ac.setPhoneNumber(res.getParameter("txtPhone"));
        ac.setAddress(res.getParameter("ttAddress"));
        ac.setImg(res.getParameter("txtImg"));
        ac.setAccountId(userId);
        System.out.println(ac.toString());
        accountService.update(ac);
        return "redirect:/Parents/ParentsProfile";
    }

    // Dức
    @RequestMapping("/childprofile")
    public String showchild(Model model,HttpSession session){
        Account accSession = (Account) session.getAttribute("acc");
        Optional<Account> acc= accountService.getAccount(accSession.getAccountId());
        acc.ifPresent(ac -> model.addAttribute("Account",ac));

       if( acc.isPresent()){
           List<Kindergartner> listKinder = kindergartnerService.getListKinderByIdParent(acc.get().getAccountId());
           model.addAttribute("listKinder",listKinder);
           if(listKinder.size()>0){
               model.addAttribute("Kinder",listKinder.get(0));
               StudyRecord studyRecord = studyRecordService.getStudyRecordByIdKinderId(listKinder.get(0).getKinderId());
               model.addAttribute("studyRecord",studyRecord);
           }
        }
        return "Parents/childprofile";
    }

//    @RequestMapping(value = "/edit",method = RequestMethod.GET)
//    public String editchild(@RequestParam("id") Integer userId, Model model){
//        Optional<Kindergartner> kinder = kindergartnerService.getById(userId);
//        kinder.ifPresent(u -> model.addAttribute("AccKinder", u));
//        Optional<Clazz> cls= clazzService .getById(2);
//        cls.ifPresent(cl-> model.addAttribute("ListClazz",cl));
//        Optional<Account> acc=accountService.getAccount(2);
//        acc.ifPresent(ac -> model.addAttribute("Accc",ac));
//
//        return "Parents/childupdateprofile";
//    }
//
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public String update(@RequestParam("id") Integer userId, HttpServletRequest res) throws Exception {
//        Kindergartner kn = new Kindergartner();
//        Optional<Kindergartner> opKin = kindergartnerService.getById(userId);
//        if(opKin.isPresent()){
//            kn = opKin.get();
//        }
//        kn.setFirstName(res.getParameter("txtFirstName"));
//        kn.setLastName(res.getParameter("txtLastName"));
//        boolean gen = !res.getParameter("gender").equals("female");
//        kn.setGender(gen);
//        kn.setDob(res.getParameter("dob"));
//        kindergartnerService.update(kn);
//        return "redirect:/kinder/";
//    }
}

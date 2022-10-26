package com.example.SWP_1631.controller;

import com.example.SWP_1631.entity.*;
import com.example.SWP_1631.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import org.modelmapper.ModelMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class Admin {


    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleSer;

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private StudyRecordService studyRecordService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ActivityService activityService;

    @GetMapping("/")
    public String view(Model model) {
        List<Account> listAcc = accountService.getListAccount();
        List<Role> listRo = roleSer.getAllRole();
        model.addAttribute("liseA", listAcc);
        model.addAttribute("listR", listRo);
        return "admin/adminAccount";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String viewSearch(HttpServletRequest request, Model model) {
        if (request.getParameter("search") == null) {
            return "redirect:/admin/";
        }
        String search = request.getParameter("search").trim();
        search = search.replaceAll("\\s\\s+", " ").trim();
        List<Account> listAcc = accountService.getListAccount();
        List<Role> listRo = roleSer.getAllRole();
        model.addAttribute("liseA", listAcc);
        model.addAttribute("listR", listRo);
        return "admin/adminAccount";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") Integer userId, Model model) {
        accountService.delete(userId);
        return "redirect:/admin/";
    }

    @RequestMapping(value = "/add-admin")
    public String addUser(Model model) {
        List<Role> listRo = roleSer.getAllRole();
        model.addAttribute("listR", listRo);
        model.addAttribute("Account", new Account());
        return "admin/adminAddAccount";
    }

    @RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
    public String save(Account user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
        accountService.save(user);
        return "redirect:/admin/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editUser(@RequestParam("id") Integer userId, Model model) {
        Optional<Account> userEdit = accountService.getAccount(userId);
        userEdit.ifPresent(user -> model.addAttribute("Account", user));
        List<Role> listRo = roleSer.getAllRole();
        model.addAttribute("listR", listRo);
        return "admin/updateAccount";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("id") Integer userId, HttpServletRequest res) throws Exception {
        Account ac = new Account();
        Optional<Account> userDB = accountService.getAccount(userId);

        if (userDB.isPresent()) {
            ac = userDB.get();
            if (!userDB.get().getPassword().equals(res.getParameter("txtPassword"))) {
                ac.setPassword(BCrypt.hashpw(res.getParameter("txtPassword").trim(), BCrypt.gensalt(12)));
            } else {
                ac.setPassword(res.getParameter("txtPassword").trim());
            }
        }
        ac.setFirstName(res.getParameter("txtFirstName").trim());
        ac.setLastName(res.getParameter("txtLastName").trim());
        boolean gen = !res.getParameter("gender").equals("female");
        ac.setGender(gen);
        ac.setEmail(res.getParameter("txtEmail").trim());
        ac.setDob(res.getParameter("dob"));
        ac.setPhoneNumber(res.getParameter("txtPhone").trim());
        ac.setAddress(res.getParameter("ttAddress").trim());
        ac.setImg(res.getParameter("txtImg").trim());
        Role role = new Role();
        role.setRoleID(Integer.parseInt(res.getParameter("roleUp")));
        ac.setRole(role);
        ac.setAccountId(userId);
        accountService.update(ac);
        return "redirect:/admin/";
    }

    @GetMapping("/clazz")
    public String viewClass(Model model) {
        List<Clazz> listClazz = clazzService.getAllClazz();
        model.addAttribute("listClazz", listClazz);
        return "admin/class/adminClassPage";
    }

    @RequestMapping(value = "/deleteclazz", method = RequestMethod.GET)
    public String deleteClazz(@RequestParam("id") Integer userId, Model model) {
        studyRecordService.deleteByIdClass(userId);
        clazzService.deleteById(userId);
        return "redirect:/admin/clazz";
    }

    @RequestMapping(value = "/editclazz", method = RequestMethod.GET)
    public String editClazz(@RequestParam("id") Integer userId, Model model) {
        Optional<Clazz> userEdit = clazzService.getById(userId);
        userEdit.ifPresent(user -> model.addAttribute("Clazz", user));
        System.out.println(userEdit.get().getClassDescription());
        List<Account> listTeacher = accountService.getListAccountByIdRole(3);
        model.addAttribute("listTeacher", listTeacher);
        return "admin/class/adminClasstUpdate";
    }

    @RequestMapping(value = "/updateClazz", method = RequestMethod.POST)
    public String updateClazz(@RequestParam("id") Integer userId, Model model, HttpServletRequest res) {
        Optional<Clazz> userEdit = clazzService.getById(userId);
        if (userEdit.isPresent()) {
            Clazz cal = userEdit.get();
            Optional<Account> userDB = accountService.getAccount(Integer.parseInt(res.getParameter("slTeacher")));
            cal.setAccount(userDB.get());
            cal.setGrade(Integer.parseInt(res.getParameter("txtGrade")));
            cal.setClassDescription(res.getParameter("taDesClass"));
            clazzService.save(cal);
        }
        return "redirect:/admin/clazz";
    }

    @RequestMapping(value = "/addadminClazz")
    public String addClazz(Model model) {
        List<Account> listTeacher = accountService.getListAccountByIdRole(3);
        model.addAttribute("listTeacher", listTeacher);
        model.addAttribute("Clazz", new Clazz());
        return "admin/class/adminClassAdd";
    }

    @RequestMapping(value = "/saveClazz", method = RequestMethod.POST)
    public String saveClazz(Clazz user) {
        clazzService.save(user);
        return "redirect:/admin/";
    }

    @GetMapping("/scheduleT")
    public String viewSchedule(Model model, HttpServletRequest request, HttpSession session) {
        String classid_raw = request.getParameter("cid");
        int classid = 1;
        if (classid_raw == null) {
            classid = 1;
            Integer cid = (Integer) session.getAttribute("cid");
            if (cid != null) {
                classid = cid;
            }
        } else {
            try {
                classid = Integer.parseInt(classid_raw);
            } catch (Exception e) {

            }
        }
        model.addAttribute("cid_raw", classid);
        session.setAttribute("cid", classid);
        LinkedHashMap<LocalDate, String> allWeeks = scheduleService.getAllWeeksInYear(2022);
        model.addAttribute("weeks", allWeeks);

        List<Clazz> listClass = clazzService.getAllClazz();
        model.addAttribute("listClass", listClass);

        List<Activity> listActivity = activityService.getAll();
        model.addAttribute("activity", listActivity);

        //**
        String date = request.getParameter("recentMonday");
        String date2 = date;

        if (date != null) {
            try {
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                Date d = sdf1.parse(date);

                SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
                date = sdf2.format(d);
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            date = scheduleService.firstDayOfWeek(new Date());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date3 = "";
            try {
                Date d = sdf.parse(date);
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                date3 = sdf.format(d);
            } catch (Exception e) {
                System.out.println(e);
            }
            session.setAttribute("recentMonday", date3);
        }
        ScheduleDetails sde = scheduleService.getScheduleDetailsByClassDate(classid, date);
        model.addAttribute("scheduleDetails", sde);

        //return true date
        String action = request.getParameter("action");
        if (action == null) {
            LocalDate now = LocalDate.now();
            LocalDate firstDayOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

            model.addAttribute("firstMonday", firstDayOfWeek);
        } else {

            model.addAttribute("recentMonday", date2);
//            session.setAttribute("recentMonday", date);

        }
        return "admin/class/adminClassAdd";
    }
}

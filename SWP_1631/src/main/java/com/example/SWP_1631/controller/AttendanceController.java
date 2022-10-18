package com.example.SWP_1631.controller;

import com.example.SWP_1631.entity.Account;
import com.example.SWP_1631.entity.Role;
import com.example.SWP_1631.service.AccountService;
import com.example.SWP_1631.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/teacher")
public class AttendanceController {

    @Autowired
    private AccountService accSer;

    @Autowired
    private RoleService roleSer;

    @RequestMapping("/checkAttendence")
    public String atten(Model model){
        return "teacher/checkAttendence";
    }

    @GetMapping("/")
    public String profile( Model model){
        Optional<Account> teacherFound = accSer.getAccount(10);
        teacherFound.ifPresent(teacher -> model.addAttribute("Teacher", teacher));
        return "teacher/teacherProfile";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String update(@RequestParam("id") Integer id,Model model){
        Optional<Account> teacherFound = accSer.getAccount(id);
        teacherFound.ifPresent(teacher -> model.addAttribute("Teacher", teacher));
        return "teacher/teacherUpdate";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("id") Integer userId, HttpServletRequest res) throws Exception {
        Optional<Account> ac2 = accSer.getAccount(userId);
        Account ac = new Account();
        if(ac2.isPresent()){
            ac.setPassword(ac2.get().getPassword());
            ac.setRole(ac2.get().getRole());
            ac.setImg(ac2.get().getImg());
        }
        ac.setFirstName(res.getParameter("txtFirstName"));
        ac.setLastName(res.getParameter("txtLastName"));
        boolean gen = !res.getParameter("gender").equals("female");
        ac.setGender(gen);
        ac.setEmail(res.getParameter("txtEmail"));
        ac.setDob(res.getParameter("dob"));
        ac.setPhoneNumber(res.getParameter("txtPhone"));
        ac.setAddress(res.getParameter("ttAddress"));
        ac.setAccountId(userId);
        System.out.println(ac);
        accSer.update(ac);
        return "redirect:/teacher/";
    }
}

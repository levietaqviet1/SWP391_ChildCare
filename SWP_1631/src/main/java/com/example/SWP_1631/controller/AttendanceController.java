package com.example.SWP_1631.controller;

import com.example.SWP_1631.entity.Account;
import com.example.SWP_1631.service.AccountService;
import com.example.SWP_1631.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/teacherProfile")
    public String profile(@RequestParam("id") Integer id, Model model){
        Optional<Account> teacherFound = accSer.getAccount(id);
        teacherFound.ifPresent(teacher -> model.addAttribute("Teacher", teacher));
        return "teacher/teacherProfile";
    }

    @RequestMapping("/update")
    public String update(@RequestParam("id") Integer id,Model model){
        Optional<Account> teacherFound = accSer.getAccount(id);
        teacherFound.ifPresent(teacher -> model.addAttribute("Teacher", teacher));
        return "teacher/teacherUpdate";
    }
}

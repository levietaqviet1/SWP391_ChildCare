package com.example.SWP_1631.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping("/")
    public String init(Model model) {
//        String name = "Viet";
//        model.addAttribute("long",name);
        return "index";
    }

    @RequestMapping("/loginSuccess")
    public String ViewS(HttpSession session) {
        if (session.getAttribute("VaiTro").equals("admin")) {
            return "redirect:/admin/";
        }
        if (session.getAttribute("VaiTro").equals("parent")) {
            return "redirect:/parents/ParentsProfile";
        }
        if (session.getAttribute("VaiTro").equals("teacher")) {
            return "redirect:/teacher/";
        }
        return "index";
    }

    @RequestMapping("/checkAttendence")
    public String atten(Model model) {
        return "teacher/checkAttendence";
    }

    @RequestMapping("/register")
    public String req(Model model) {
        return "register";
    }

    @RequestMapping("/childregister")
    public String chireq(Model model) {
        return "childrenregister/childregister";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping("/userAccountInfo")
    public String userAccountInfo(Model model) {
        return "home";
    }

    // khi người dùng logout khỏi hệ thống
    @RequestMapping(value = "/logoutSuccessful")
    public String logoutSuccessfulPage(Model model,HttpSession session) {
        if (session.getAttribute("VaiTro") != null) {
            session.removeAttribute("VaiTro");
        }
        if (session.getAttribute("acc") != null) {
            session.removeAttribute("acc");
        }
        model.addAttribute("title", "Logout");
        return "redirect:/home/";
    }


}

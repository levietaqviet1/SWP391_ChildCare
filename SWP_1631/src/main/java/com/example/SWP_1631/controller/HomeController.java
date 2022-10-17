package com.example.SWP_1631.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping("/")
    public String init(Model model){
//        String name = "Viet";
//        model.addAttribute("long",name);
        return "index";
    }

    @RequestMapping("/register")
    public String req(Model model){
        return "register";
    }

    @RequestMapping("/childregister")
    public String chireq(Model model){
        return "childrenregister/childregister";
    }

    @RequestMapping("/login")
    public String login(Model model){
        return "login";
    }

    @RequestMapping("/userAccountInfo")
    public String userAccountInfo(Model model){
        return "home";
    }

    // khi người dùng logout khỏi hệ thống
    @RequestMapping(value = "/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "home";
    }


}

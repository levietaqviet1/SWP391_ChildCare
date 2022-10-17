package com.example.SWP_1631.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping
public class UpdateParentController {
    @GetMapping("")
    public String upDate(){
        return "";
    }
}

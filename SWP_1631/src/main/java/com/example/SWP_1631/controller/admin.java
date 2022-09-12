package com.example.SWP_1631.controller;

import com.example.SWP_1631.entity.Account;
import com.example.SWP_1631.entity.Role;
import com.example.SWP_1631.service.AccountService;
import com.example.SWP_1631.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import org.modelmapper.ModelMapper;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class admin{

//    @Autowired
//    private ModelMapper modelMappe;

    @Autowired
    private AccountService accSer;

    @Autowired
    private RoleService roleSer;

    @GetMapping("/")
    public String view(Model model){
        List<Account> listAcc = accSer.getListAccount();
        List<Role> listRo = roleSer.getAllRole();
        model.addAttribute("liseA",listAcc );
        model.addAttribute("listR",listRo );
        return "admin/adminAccount";
    }

    @PutMapping("/Update{id}")
    public Account update(@PathVariable Integer id, @RequestBody Account user) {
        return accSer.update(id, user);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        accSer.delete(id);
        List<Account> listAcc = accSer.getListAccount();
        List<Role> listRo = roleSer.getAllRole();
        model.addAttribute("liseA",listAcc );
        model.addAttribute("listR",listRo );
        return "admin/adminAccount";
    }

    @GetMapping("/add-admin")
    public String viewAdd(Model model){
        List<Role> listRo = roleSer.getAllRole();
        Account n= new Account();
        model.addAttribute("listR",listRo );
        model.addAttribute("Account",n );
        return "admin/adminAddAccount";
    }

    @PostMapping("/saveAccount")
    public String save(@ModelAttribute("txtEmail") String acc){

        return acc;
    }




}

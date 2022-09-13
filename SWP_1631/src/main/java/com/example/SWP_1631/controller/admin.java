package com.example.SWP_1631.controller;

import com.example.SWP_1631.entity.Account;
import com.example.SWP_1631.entity.Role;
import com.example.SWP_1631.service.AccountService;
import com.example.SWP_1631.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

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

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editUser(@RequestParam("id") Integer userId, Model model) {
        Optional<Account> userEdit = accSer.getAccount(userId);
        userEdit.ifPresent(user -> model.addAttribute("account", user));
        return "editUser";
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") Integer userId, Model model) {
        accSer.delete(userId);
        List<Account> listAcc = accSer.getListAccount();
        List<Role> listRo = roleSer.getAllRole();
        model.addAttribute("liseA",listAcc );
        model.addAttribute("listR",listRo );
        return "admin/adminAccount";
    }

    @RequestMapping(value = "/add-admin")
    public String addUser(Model model) {
        List<Role> listRo = roleSer.getAllRole();
        model.addAttribute("listR",listRo );
        model.addAttribute("Account", new Account());
        return "admin/adminAddAccount";
    }

    @RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
    public String save(Account user) {
//        accSer.save(user);
        return "index";
    }




}

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
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class admin {

//    @Autowired
//    private ModelMapper modelMappe;

    @Autowired
    private AccountService accSer;

    @Autowired
    private RoleService roleSer;

    @GetMapping("/")
    public String view(Model model) {
        List<Account> listAcc = accSer.getListAccount();
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
        List<Account> listAcc = accSer.getListAccount();
        List<Role> listRo = roleSer.getAllRole();
        model.addAttribute("liseA", listAcc);
        model.addAttribute("listR", listRo);
        return "admin/adminAccount";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") Integer userId, Model model) {
        accSer.delete(userId);
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
        accSer.save(user);
        return "redirect:/admin/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editUser(@RequestParam("id") Integer userId, Model model) {
        Optional<Account> userEdit = accSer.getAccount(userId);
        userEdit.ifPresent(user -> model.addAttribute("Account", user));
        List<Role> listRo = roleSer.getAllRole();
        model.addAttribute("listR", listRo);
        return "admin/updateAccount";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("id") Integer userId, HttpServletRequest res) throws Exception {
        Account ac = new Account();
        ac.setFirstName(res.getParameter("txtFirstName"));
        ac.setLastName(res.getParameter("txtLastName"));
        boolean gen = !res.getParameter("gender").equals("female");
        ac.setGender(gen);
        ac.setEmail(res.getParameter("txtEmail"));
        ac.setPassword(res.getParameter("txtPassword"));
        ac.setDob(res.getParameter("dob"));
        ac.setPhoneNumber(res.getParameter("txtPhone"));
        ac.setAddress(res.getParameter("ttAddress"));
        ac.setImg(res.getParameter("txtImg"));
        Role role = new Role();
        role.setRoleID(Integer.parseInt(res.getParameter("roleUp")));
        ac.setRole(role);
        System.out.println("OOOOOOOOOOOOOOOOOO id" + userId);
        System.out.println(ac);
        ac.setAccountId(userId);
        accSer.update(ac);
        return "redirect:/admin/";
    }


}

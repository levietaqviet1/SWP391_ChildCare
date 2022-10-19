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
@RequestMapping("/Parents")
public class ParentController {
    @Autowired
    private AccountService accSer;

    @Autowired
    private RoleService roleSer;

    @RequestMapping("/")
    public String View(Model model){
        return "Parents/ParentProfile";
    }

    @RequestMapping("/ParentsProfile")
    public String profile( Model model){
        Optional<Account> teacherFound = accSer.getAccount(4);
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
        List<Account> listAcc = accSer.getListAccount();
        List<Role> listRo = roleSer.getAllRole();
        model.addAttribute("Parents", listAcc);
        model.addAttribute("listR", listRo);
        return "Parents/ParentProfile";
    }
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editUser(@RequestParam("id") Integer userId, Model model) {
        Optional<Account> userEdit = accSer.getAccount(userId);
        userEdit.ifPresent(user -> model.addAttribute("Parents", user));
        List<Role> listRo = roleSer.getAllRole();
        model.addAttribute("listR", listRo);
        return "Parents/parentUpdateProfile";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("id") Integer userId, HttpServletRequest res) throws Exception {
        Optional<Account> ac2 = accSer.getAccount(userId);
        Account ac = new Account();
        if( ac2.isPresent()){
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
        accSer.update(ac);
        return "redirect:/Parents/ParentsProfile";
    }
}

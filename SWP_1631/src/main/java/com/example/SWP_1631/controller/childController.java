package com.example.SWP_1631.controller;

import com.example.SWP_1631.entity.Account;
import com.example.SWP_1631.entity.Clazz;
import com.example.SWP_1631.entity.Role;
import com.example.SWP_1631.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class childController {

    @Autowired
    ClazzService clazz;

    @Autowired
    private ClazzService clazzSer;
    @GetMapping("/child")
    public String view(Model model){
        List<Clazz> list = clazz.getAllClazz();
        model.addAttribute("ListClazz",list);
        return "childrenregister/childregister";
    }


//    @RequestMapping(value = "/updateChild", method = RequestMethod.POST)
//    public String update(@RequestParam("id") Integer userId, HttpServletRequest res) throws Exception {
//        Account ac = new Account();
//        ac.setFirstName(res.getParameter("ChildFirstName"));
//        ac.setLastName(res.getParameter("ChildLastName"));
//        boolean gen = !res.getParameter("gender").equals("female");
//        ac.setGender(gen);
//        ac.setDob(res.getParameter("dob"));
//        Role role = new Role();
//        role.setRoleID(Integer.parseInt(res.getParameter("roleUp")));
//        ac.setRole(role);
//        System.out.println("OOOOOOOOOOOOOOOOOO id" + userId);
//        System.out.println(ac);
//        ac.setAccountId(userId);
//        clazzSer.update(ac);
//        return "child";
//    }
}

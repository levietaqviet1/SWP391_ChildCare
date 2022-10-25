package com.example.SWP_1631.controller;

import com.example.SWP_1631.entity.Account;
import com.example.SWP_1631.entity.Clazz;
import com.example.SWP_1631.entity.Kindergartner;
import com.example.SWP_1631.entity.Role;
import com.example.SWP_1631.service.ClazzService;
import com.example.SWP_1631.service.KindergartnerService;
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
    private ClazzService clazzService;

    @Autowired
    private KindergartnerService kindergartnerService;

    @GetMapping("/child")
    public String view(Model model) {
        List<Clazz> list = clazzService.getAllClazz();
        model.addAttribute("ListClazz", list);
        return "childrenregister/childregister";
    }

//    @RequestMapping(value = "/saveInforChild", method = RequestMethod.POST)
//    public String save(Kindergartner child) {
//        kinderSer.save(child);
//        return "childrenregister/childregister";
//    }


    @RequestMapping(value = "/updateChild", method = RequestMethod.POST)
    public String update(HttpServletRequest res) throws Exception {
        Kindergartner ch = new Kindergartner();
        ch.setFirstName(res.getParameter("ChildFirstName"));
        ch.setLastName(res.getParameter("ChildLastName"));
        boolean gen = !res.getParameter("flexRadioDefault").equals("female");
        ch.setGender(gen);
        ch.setDob(res.getParameter("dob"));
        kindergartnerService.save(ch);
        return "redirect:/child";
    }
}

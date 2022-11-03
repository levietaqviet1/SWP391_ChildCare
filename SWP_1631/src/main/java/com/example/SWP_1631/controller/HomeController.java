package com.example.SWP_1631.controller;

import com.example.SWP_1631.Utill.SendMail;
import com.example.SWP_1631.Utill.Utill;
import com.example.SWP_1631.entity.Account;
import com.example.SWP_1631.entity.Role;
import com.example.SWP_1631.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private AccountService accountService;
    private Utill utill = new Utill();

    @RequestMapping("/")
    public String init(Model model, HttpSession session) {
//        String name = "Viet";
//        model.addAttribute("long",name);
        if (session.getAttribute("VaiTro") != null) {
            return "redirect:/home/loginSuccess";
        }
        return "index";
    }

    @RequestMapping("/loginSuccess")
    public String ViewS(HttpSession session) {
        if (session.getAttribute("VaiTro").equals("ROLE_ADMIN")) {
            return "redirect:/admin/";
        }
        if (session.getAttribute("VaiTro").equals("ROLE_PARENT")) {
            return "redirect:/parents/ParentsProfile";
        }
        if (session.getAttribute("VaiTro").equals("ROLE_TEACHER")) {
            return "redirect:/teacher/";
        }
        return "index";
    }

    @RequestMapping("/register")
    public String req(Model model, HttpSession session) {
        String s = utill.RandomStringg(20);
        session.setAttribute("registerSession", s);
        session.setMaxInactiveInterval(60 * 10);
        model.addAttribute("accountTam", new Account());
        model.addAttribute("exitEmail", false);
        model.addAttribute("isRegis", false);
        return "register";
    }

    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public String createAccount(Model model, HttpSession session, HttpServletRequest res) {
        if (res.getParameter("id") != null && res.getParameter("id").equals(session.getAttribute("registerSession"))) {
            Account account = new Account();
            account.setFirstName(res.getParameter("fname"));
            account.setLastName(res.getParameter("lname"));
            boolean gender = res.getParameter("gender").equals("male");
            account.setGender(gender);
            account.setAddress(res.getParameter("address"));
            account.setPhoneNumber(res.getParameter("phone"));
            account.setEmail(res.getParameter("email"));
            try {
                account.setDob(res.getParameter("dob"));
            } catch (Exception e) {
            }
            if (accountService.checkEmailExitInDatabase(account.getEmail())) {
                model.addAttribute("accountTam", account);
                model.addAttribute("exitEmail", true);
                model.addAttribute("isRegis", false);
                return "register";
            } else {
                account.setRole(new Role(2));
                account.setPassword(utill.RandomStringg(6));
                session.setAttribute("accountTamThoiRegister", account);
                String code = utill.RandomStringg(6) + utill.RandNum(2);
                session.setAttribute("codeRegister", code);
                session.setMaxInactiveInterval(60 * 15);
                SendMail sendMail = new SendMail();
                String gen = "Male";
                if (!account.isGender()) {
                    gen = "FeMale";
                }
                String html = "" +
                        "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    <title>AA</title>\n" +
                        "    <style>\n" +
                        "        *{\n" +
                        "            box-sizing: border-box;\n" +
                        "            margin: 0;\n" +
                        "            padding: 0;\n" +
                        "        }\n" +
                        "        body{\n" +
                        "            background-color: bisque;\n" +
                        "        }\n" +
                        "        .container{\n" +
                        "            margin: 10%;\n" +
                        "            background-color: rgb(223, 205, 171);\n" +
                        "        }\n" +
                        "        .code{\n" +
                        "            width: 20%;\n" +
                        "            margin: 0 10%;\n" +
                        "            background-color: rgb(241, 132, 132);\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<div class=\"container\"> \n" +
                        "    Hello " + account.getFirstName() + ",<br/>\n" +
                        "    For security purposes, you must enter the code below to verify your account to access<br/>\n" +
                        "    The code will only work for 15 minutes and if you request a new code, this code will stop working.\n" +
                        "    <div class=\"code\">\n" +
                        "        Account verification code:<br/>\n" +
                        "        <h3 style=\"color: red;\">" + code + "</h3>\n" +
                        "    </div>\n" +
                        "    <br/>\n" +
                        "    You can check information below:\n" +
                        "    <table border=\"1\" style=\"\">\n" +
                        "        <tr>\n" +
                        "            <th>First Name</th>\n" +
                        "            <th>Last Name</th>\n" +
                        "            <th>Gender</th>\n" +
                        "            <th>Email</th>\n" +
                        "            <th>Date</th>\n" +
                        "            <th>Phone Number</th>\n" +
                        "            <th>Address</th>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td>" + account.getFirstName() + "</td>\n" +
                        "            <td>" + account.getLastName() + "</td>\n" +
                        "            <td>" + gen + "</td>\n" +
                        "            <td>" + account.getEmail() + "</td>\n" +
                        "            <td>" + account.getDob() + "</td>\n" +
                        "            <td>" + account.getPhoneNumber() + "</td>\n" +
                        "            <td>" + account.getAddress() + "</td>\n" +
                        "        </tr>\n" +
                        "         \n" +
                        "    </table>\n" +
                        "</div>\n" +
                        "    \n" +
                        "</body>\n" +
                        "</html>";
                sendMail.sendFuncition(account.getEmail(),code+" is your Confirm Your email!! !",html);
                model.addAttribute("isRegis", true);
                model.addAttribute("accountTam", new Account());
                model.addAttribute("exitEmail", false);
                model.addAttribute("doneRegis", false);
                String s = utill.RandomStringg(15) + utill.RandNum(10);
                session.setAttribute("registerSession", s);
                return "register";
            }

        }
        return "redirect:/home/register";
    }


    @RequestMapping(value = "/checkRegister", method = RequestMethod.POST)
    public String checkRegister(Model model, HttpSession session, HttpServletRequest res) {
        if (session.getAttribute("accountTamThoiRegister") != null) {
            if (res.getParameter("id") != null && res.getParameter("id").trim().equals(session.getAttribute("registerSession"))) {
//                codeRegister 101010 là mã code dự phòng kẻo trên trường ko gửi mã về gmail đc do mạng kém
                if (res.getParameter("codeRe").equals(session.getAttribute("codeRegister")) || res.getParameter("codeRe").equals("101010")) {
                    Account account = (Account) session.getAttribute("accountTamThoiRegister");
//                    ac.setPassword();
                    String pass = account.getPassword();
                    account.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt(12)));
                    accountService.save(account); // lưu vào db
                    // xuất từ db ra
                    Account user = accountService.getAccByEmail(account.getEmail());
                    String gen = "Male";
                    if (!account.isGender()) {
                        gen = "FeMale";
                    }
                    String html = "" +
                            "<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                            "    <title>AA</title>\n" +
                            "    <style>\n" +
                            "        *{\n" +
                            "            box-sizing: border-box;\n" +
                            "            margin: 0;\n" +
                            "            padding: 0;\n" +
                            "        }\n" +
                            "        body{\n" +
                            "            background-color: bisque;\n" +
                            "        }\n" +
                            "        .container{\n" +
                            "            margin: 10%;\n" +
                            "            background-color: rgb(223, 205, 171);\n" +
                            "        }\n" +
                            "        .code{\n" +
                            "            width: 20%;\n" +
                            "            margin: 0 10%;\n" +
                            "            background-color: rgb(27, 235, 27);\n" +
                            "        }\n" +
                            "    </style>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "<div class=\"container\"> \n" +
                            "    Hello,<br/>\n" +
                            "    Welcome to us<br/>\n" +
                            "     \n" +
                            "    <div class=\"code\">\n" +
                            "        Account:  <span style=\"color: rgb(255, 230, 0);\">"+account.getEmail()+"</span><br/>\n" +
                            "        Password:  <span style=\"color: rgb(255, 230, 0);\">"+pass+"</span><br/>\n" +
                            "    </div>\n" +
                            "    <br/>\n" +
                            "    You can check information below:\n" +
                            "    <table border=\"1\" style=\"\">\n" +
                            "        <tr>\n" +
                            "            <th>First Name</th>\n" +
                            "            <th>Last Name</th>\n" +
                            "            <th>Gender</th>\n" +
                            "            <th>Email</th>\n" +
                            "            <th>Date</th>\n" +
                            "            <th>Phone Number</th>\n" +
                            "            <th>Address</th>\n" +
                            "        </tr>\n" +
                            "        <tr>\n" +
                            "            <td>" + account.getFirstName() + "</td>\n" +
                            "            <td>" + account.getLastName() + "</td>\n" +
                            "            <td>" + gen + "</td>\n" +
                            "            <td>" + account.getEmail() + "</td>\n" +
                            "            <td>" + account.getDob() + "</td>\n" +
                            "            <td>" + account.getPhoneNumber() + "</td>\n" +
                            "            <td>" + account.getAddress() + "</td>\n" +
                            "        </tr>\n" +
                            "         \n" +
                            "    </table>\n" +
                            "</div>\n" +
                            "    \n" +
                            "</body>\n" +
                            "</html>" ;
                    SendMail sendMail = new SendMail();
                    sendMail.sendFuncition(account.getEmail(),"Welcome to us",html);
//                    List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
//                    GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRoleName());
//                    grantList.add(authority);
//
//                    session.setAttribute("VaiTro", user.getRole().getRoleName());
//                    session.setAttribute("acc", user);
                    session.removeAttribute("accountTamThoiRegister");
                    session.removeAttribute("codeRegister");
                    session.removeAttribute("registerSession");

                    model.addAttribute("isRegis", true);
                    model.addAttribute("accountTam", new Account());
                    model.addAttribute("exitEmail", false);
                    model.addAttribute("doneRegis", true);
                    return "register";
                } else {
                    model.addAttribute("isRegis", true);
                    model.addAttribute("accountTam", new Account());
                    model.addAttribute("exitEmail", true);
                    model.addAttribute("doneRegis", false);
                    model.addAttribute("codeFalse", res.getParameter("codeRe"));
                    return "register";
                }
            }
        }
        return "redirect:/home/register";
    }

    @RequestMapping("/login")
    public String login(Model model, HttpSession session) {
        if (session.getAttribute("accountTamThoiRegister") != null) {
            session.removeAttribute("accountTamThoiRegister");
        }
        if (session.getAttribute("registerSession") != null) {
            session.removeAttribute("registerSession");
        }
        if (session.getAttribute("VaiTro") != null) {
            if (session.getAttribute("VaiTro").equals("ROLE_ADMIN")) {
                return "redirect:/admin/";
            }
            if (session.getAttribute("VaiTro").equals("ROLE_PARENT")) {
                return "redirect:/parents/ParentsProfile";
            }
            if (session.getAttribute("VaiTro").equals("ROLE_TEACHER")) {
                return "redirect:/teacher/";
            }
        }

        return "login";
    }


    // khi người dùng logout khỏi hệ thống
    @RequestMapping(value = "/logoutSuccessful")
    public String logoutSuccessfulPage(Model model, HttpSession session) {
        if (session.getAttribute("VaiTro") != null) {
            session.removeAttribute("VaiTro");
        }
        if (session.getAttribute("acc") != null) {
            session.removeAttribute("acc");
        }
        if (session.getAttribute("cidSession") != null) {
            session.removeAttribute("cidSession");
        }
        model.addAttribute("title", "Logout");
        return "redirect:/home/";
    }


}

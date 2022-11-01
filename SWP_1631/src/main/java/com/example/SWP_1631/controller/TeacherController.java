package com.example.SWP_1631.controller;

import com.example.SWP_1631.entity.*;
import com.example.SWP_1631.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleSer;

    @Autowired
    private StudyRecordService studyRecordService;

    @Autowired
    private ClazzService clazzService;
    @Autowired
    private AttendanceService attendanceService;

    @RequestMapping("/checkAttendence")
    public String atten(Model model) {
        return "teacher/checkAttendence";
    }

    @GetMapping("/")
    public String profile(Model model, HttpSession session) {
        Account accSe = (Account) session.getAttribute("acc");
        Optional<Account> teacherFound = accountService.getAccount(accSe.getAccountId());
        teacherFound.ifPresent(teacher -> model.addAttribute("Teacher", teacher));
        return "teacher/teacherProfile";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String update(@RequestParam("id") Integer id, Model model) {
        Optional<Account> teacherFound = accountService.getAccount(id);
        teacherFound.ifPresent(teacher -> model.addAttribute("Teacher", teacher));
        return "teacher/teacherUpdate";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("id") Integer userId, HttpServletRequest res) throws Exception {
        Optional<Account> ac2 = accountService.getAccount(userId);
        Account ac = new Account();
        if (ac2.isPresent()) {
            ac.setPassword(ac2.get().getPassword());
            ac.setRole(ac2.get().getRole());
            ac.setImg(ac2.get().getImg());
        }
        ac.setFirstName(res.getParameter("txtFirstName"));
        ac.setLastName(res.getParameter("txtLastName"));
        boolean gen = !res.getParameter("gender").equals("female");
        ac.setGender(gen);
        ac.setEmail(res.getParameter("txtEmail"));
        ac.setDob(res.getParameter("dob"));
        ac.setPhoneNumber(res.getParameter("txtPhone"));
        ac.setAddress(res.getParameter("ttAddress"));
        ac.setAccountId(userId);
        System.out.println(ac);
        accountService.update(ac);
        return "redirect:/teacher/";
    }

    @GetMapping("/homeTeacher")
    public String homeTeacher(Model model, HttpSession session, HttpServletRequest res) {
        Account accSe = (Account) session.getAttribute("acc");
        String checkindate = LocalDate.now().toString(); // lấy date hiện tại
        if (res.getParameter("checkindate") != null) {
            checkindate = res.getParameter("checkindate");
        }
        Clazz clazz = clazzService.getClazzByIdAccount(accSe.getAccountId()); // lấy các class do acc đó quản lý
        List<StudyRecord> listStudyRecord = studyRecordService.getStudyRecordByIdClassId(clazz.getClazzId()); // tạo list StudyRecord
        if (listStudyRecord.isEmpty()) {
            return "redirect:/teacher/";
        }
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        for (StudyRecord kinderRecordStudy : listStudyRecord) {

            map.put(kinderRecordStudy.getKinderId().getKinderId(), 0);
        }
        List<Attendance> attendances = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date e = sdf.parse(checkindate);
            attendances = attendanceService.getAllAttendanceOfInputDay(e);
        } catch (Exception e) {

        }
        System.err.println("1");
        if (!attendances.isEmpty()) {
            System.err.println("2");
            for (Attendance a : attendances) {
                if (a.getStatus() != 0) {
                    map.replace(a.getStudentId().getKinderId(), 1);
                }
                System.err.println(a.getTeacherId() + " " + a.getStatus());
            }
        }
        model.addAttribute("clazz", clazz.getClassName());
        model.addAttribute("studentMap", map);
        model.addAttribute("checkindate", checkindate);
        model.addAttribute("listStudyRecord", listStudyRecord);
        model.addAttribute("isPast", !checkindate.equalsIgnoreCase(LocalDate.now().toString()));
        model.addAttribute("present_kids", attendances);
        return "teacher/homeTeacher";
    }

    @RequestMapping(value = "/UpdateAttendance", method = RequestMethod.POST)
    public String updatetest(@RequestParam("checkindate") String checkdate, Model model, HttpServletRequest res, HttpSession session) {
        Account accSe = (Account) session.getAttribute("acc");
        String checkindate = LocalDate.now().toString(); // lấy date hiện tại
        if (checkdate != null) {
            checkindate = checkdate;
            System.err.println("In" + checkindate);
        }
        Clazz clazz = clazzService.getClazzByIdAccount(accSe.getAccountId()); // lấy các class do acc đó quản lý
        List<StudyRecord> listStudyRecord = studyRecordService.getStudyRecordByIdClassId(clazz.getClazzId()); // tạo list StudyRecord

        for (StudyRecord list : listStudyRecord) {
            Attendance attendance = new Attendance();
            attendance.setTeacherId(accSe);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date dateFormat = sdf.parse(checkindate);
                Optional<Attendance> attendanceOptional = attendanceService.getAttendanceByStudentIdAndDateAndTeacherId(list.getKinderId().getKinderId(), dateFormat, accSe.getAccountId());
                if (attendanceOptional != null && attendanceOptional.isPresent()) {
                    attendance = attendanceOptional.get();
                    try {
                        attendance.checkDate(checkindate);
                    } catch (Exception e) {
                    }
                    attendance.setStatus(Integer.parseInt(res.getParameter("gen" + list.getKinderId().getKinderId())));
                } else {
                    try {
                        attendance.checkDate(checkindate);
                    } catch (Exception e) {
                    }
                    attendance.setStudentId(list.getKinderId());
                    attendance.setStatus(Integer.parseInt(res.getParameter("gen" + list.getKinderId().getKinderId())));
                    attendance.checkDate(LocalDate.now().toString());
                }
                attendanceService.save(attendance);
            } catch (Exception e) {

            }

        }
        return "redirect:/teacher/homeTeacher";
    }

}

package com.example.SWP_1631.service;

import com.example.SWP_1631.entity.Attendance;

import java.util.List;

public interface AttendanceService {
    public List<Attendance> getAllAttendanceByIdKinder(Integer id);

    public void deleteAttendanceByIdKinder(Integer id);
}

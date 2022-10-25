package com.example.SWP_1631.service.implement;

import com.example.SWP_1631.entity.Attendance;
import com.example.SWP_1631.repository.AttendanceRepository;
import com.example.SWP_1631.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public List<Attendance> getAllAttendanceByIdKinder(Integer id) {
        return attendanceRepository.getAllAttendanceByIdKinder(id);
    }
}

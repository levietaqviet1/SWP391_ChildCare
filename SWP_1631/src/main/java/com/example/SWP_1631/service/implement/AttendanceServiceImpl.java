package com.example.SWP_1631.service.implement;

import com.example.SWP_1631.repository.AttendanceRepository;
import com.example.SWP_1631.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceRepository attendRes;
}

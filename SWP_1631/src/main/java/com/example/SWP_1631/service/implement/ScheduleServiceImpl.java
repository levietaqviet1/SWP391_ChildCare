package com.example.SWP_1631.service.implement;

import com.example.SWP_1631.repository.ScheduleRepository;
import com.example.SWP_1631.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
}

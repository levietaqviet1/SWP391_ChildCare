package com.example.SWP_1631.service.implement;

import com.example.SWP_1631.repository.ActivityRepository;
import com.example.SWP_1631.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityRepository accRes;
}

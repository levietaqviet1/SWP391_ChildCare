package com.example.SWP_1631.service.implement;

import com.example.SWP_1631.repository.ClazzRepository;
import com.example.SWP_1631.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzRepository classRes;
}

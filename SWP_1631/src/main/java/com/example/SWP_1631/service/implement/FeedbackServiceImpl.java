package com.example.SWP_1631.service.implement;

import com.example.SWP_1631.repository.FeedbackRepository;
import com.example.SWP_1631.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRes;
}

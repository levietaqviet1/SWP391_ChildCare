package com.example.SWP_1631.service.implement;

import com.example.SWP_1631.repository.KindergartnerRepository;
import com.example.SWP_1631.service.KindergartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KindergartnerServiceImpl implements KindergartnerService {
    @Autowired
    private KindergartnerRepository kinderRes;
}

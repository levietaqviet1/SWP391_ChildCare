package com.example.SWP_1631.service.implement;

import com.example.SWP_1631.entity.Account;
import com.example.SWP_1631.entity.Clazz;
import com.example.SWP_1631.repository.ClazzRepository;
import com.example.SWP_1631.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzRepository classRes;

    @Override
    public List<Clazz> getAllClazz() {
        return classRes.findAll();
    }

    @Override
    public void update(Account child) {

    }

    @Override
    public Optional<Clazz> getById(int i) {
        Optional<Clazz> clazz = classRes.findById(i);
        return clazz;
    }
}

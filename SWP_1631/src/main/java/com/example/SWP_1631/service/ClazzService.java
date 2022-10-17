package com.example.SWP_1631.service;

import com.example.SWP_1631.entity.Account;
import com.example.SWP_1631.entity.Clazz;

import java.util.List;

public interface ClazzService {
    public List<Clazz> getAllClazz();

    public void update(Account child);
}

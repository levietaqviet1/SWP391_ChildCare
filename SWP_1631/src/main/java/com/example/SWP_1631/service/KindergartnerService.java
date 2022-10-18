package com.example.SWP_1631.service;

import com.example.SWP_1631.entity.Account;
import com.example.SWP_1631.entity.Kindergartner;

import java.util.List;
import java.util.Optional;

public interface KindergartnerService {

    List<Kindergartner> getListKinder();


    void delete(Integer KinderId);


    void update(Kindergartner kd);


    void save(Kindergartner kinder);

    Optional<Kindergartner> getKindergartnerById(Integer id);
}

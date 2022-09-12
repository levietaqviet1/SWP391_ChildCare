package com.example.SWP_1631.repository;

import com.example.SWP_1631.entity.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClazzRepository extends JpaRepository<Clazz,Integer> {
}

package com.example.SWP_1631.repository;

import com.example.SWP_1631.entity.Study_Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRecordRepository extends JpaRepository<Study_Record, Integer> {
}

package com.example.SWP_1631.repository;

import com.example.SWP_1631.entity.Account;
import com.example.SWP_1631.entity.StudyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRecordRepository extends JpaRepository<StudyRecord, Integer> {
    @Query("SELECT u FROM StudyRecord u WHERE u.kinderId.KinderId =:id")
    public StudyRecord getStudyRecordByIdKinderId(@Param("id") Integer id);


}

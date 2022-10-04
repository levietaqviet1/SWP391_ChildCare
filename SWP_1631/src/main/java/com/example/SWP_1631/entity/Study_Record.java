package com.example.SWP_1631.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "study_record")
public class Study_Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_record_id")
    private int recordId;
    @Column(name = "class_id")
    private int classId;
    @Column(name = "kinder_id")
    private int kinderId;
    @Column(name = "study_year")
    private int studyYear;


}

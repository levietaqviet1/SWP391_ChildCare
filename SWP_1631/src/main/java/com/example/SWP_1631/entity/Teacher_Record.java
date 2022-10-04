package com.example.SWP_1631.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "teacher_record")
public class Teacher_Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private int recordId;
    @Column(name = "teacher_id")
    private int teacherId;
    @Column(name = "description")
    private int description;
}
package com.example.SWP_1631.entity;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@Table(name = "record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private int recordId;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Kindergartner KinderId;

    @Column(name = "note")
    private String Note;
    @ManyToOne
    @JoinColumn(name = "criteria_id")
    private Criteria criteriaid;

    @Column(name = "semester")
    private String Semester;

}

package com.example.SWP_1631.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private int scheduleId;

    @Column(name = "class_id")
    private String classId;

    @Column(name = "activity_id")
    private String activityId;

    @Column(name = "slot_id")
    private String slotId;

    @Column(name = "schedule_date")
    private String scheduleDate;
}

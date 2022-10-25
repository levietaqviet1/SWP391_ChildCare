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

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Clazz clazzId;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activityid;

    @ManyToOne
    @JoinColumn(name = "slot_id")
    private Slot slotId;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "")
    @Column(name = "schedule_date")
    private Date scheduleDate;

    public Date getDob() {
        return scheduleDate;
    }

    public void setDob(String dob) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.scheduleDate = formatter.parse(dob);
        } catch (Exception e) {
            String sDate = "17/07/2017";
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
            this.scheduleDate = date;
        }

    }
}

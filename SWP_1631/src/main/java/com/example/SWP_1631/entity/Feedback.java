package com.example.SWP_1631.entity;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private int feedbackId;

    @Column(name = "kid_id")
    private int kidId;

    @Column(name = "teacher_id")
    private int teacherId;

    @Column(name = "fb_content")
    private String FbContent;

    @Column(name = "rating")
    private String rating;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "")
    @Column(name = "fb_date")
    private Date FbDate;

    public Date getDob() {
        return FbDate;
    }

    public void setDob(String dob) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.FbDate = formatter.parse(dob);
        } catch (Exception e) {
            String sDate = "17/07/2017";
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
            this.FbDate = date;
        }

    }

}

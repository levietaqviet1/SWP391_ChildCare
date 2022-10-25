package com.example.SWP_1631.service;

import com.example.SWP_1631.entity.StudyRecord;

public interface StudyRecordService {

    public StudyRecord getStudyRecordByIdKinderId(Integer id);

    public void save(StudyRecord studyRecord);
}

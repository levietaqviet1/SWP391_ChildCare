package com.example.SWP_1631.service.implement;

import com.example.SWP_1631.entity.StudyRecord;
import com.example.SWP_1631.repository.StudyRecordRepository;
import com.example.SWP_1631.service.StudyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudyRecordServiceImpl implements StudyRecordService {
    @Autowired
    private StudyRecordRepository studyRecordRepository;

    @Override
    public StudyRecord getStudyRecordByIdKinderId(Integer id) {
        return studyRecordRepository.getStudyRecordByIdKinderId(id);
    }

    @Override
    public void save(StudyRecord studyRecord) {
        studyRecordRepository.save(studyRecord);
    }
}

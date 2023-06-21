package com.student.server.services;

import com.student.server.models.ResponseObject;
import com.student.server.models.StudentInfo;
import com.student.server.repositories.StudentInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentInfoServiceImpl implements StudentInfoService {
    @Autowired
    StudentInfoRepository studentInfoRepository;

    @Override
    public Iterable<StudentInfo> getAllStudentInfo() {
        return studentInfoRepository.findAll();
    };

    @Override
    public ResponseEntity<ResponseObject> createNewStudentInfo(StudentInfo studentInfo) {
        if (studentInfo.getAddress().length() > 255) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ResponseObject("FAILED", "Student address length must be <= 255 characters")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Create student info successfully", studentInfoRepository.save(studentInfo))
        );
    };

    @Override
    public StudentInfo findInfoByStudentId(Integer id) {
        return studentInfoRepository.findByStudentId(id);
    };
}

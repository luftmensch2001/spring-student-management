package com.student.server.services;

import com.student.server.models.ResponseObject;
import com.student.server.models.StudentInfo;
import org.springframework.http.ResponseEntity;

public interface StudentInfoService {
    public Iterable<StudentInfo> getAllStudentInfo();
    public ResponseEntity<ResponseObject> createNewStudentInfo(StudentInfo studentInfo);
    public StudentInfo findInfoByStudentId(Integer id);
}

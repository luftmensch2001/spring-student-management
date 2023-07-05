package com.student.server.services;

import com.student.server.models.StudentInfo;
import org.springframework.data.util.Pair;

public interface StudentInfoService {
    public Iterable<StudentInfo> getAllStudentInfo();
    public Pair<Boolean, Object> createNewStudentInfo(StudentInfo studentInfo);
    public StudentInfo findInfoByStudentId(Integer id);
}

package com.student.server.servicesImpl;

import com.student.server.models.StudentInfo;
import com.student.server.repositories.StudentInfoRepository;
import com.student.server.services.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
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
    public Pair<Boolean, Object> createNewStudentInfo(StudentInfo studentInfo) {
        if (studentInfo.getAddress().length() > 255) {
            return Pair.of(false, "Student address length must be <= 255 characters");
        }
        return Pair.of(true, studentInfoRepository.save(studentInfo));
    };

    @Override
    public StudentInfo findInfoByStudentId(Integer id) {
        return studentInfoRepository.findByStudentId(id);
    };
}

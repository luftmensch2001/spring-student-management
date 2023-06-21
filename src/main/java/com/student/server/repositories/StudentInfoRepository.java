package com.student.server.repositories;

import com.student.server.models.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentInfoRepository extends JpaRepository<StudentInfo, Integer> {
    StudentInfo findByStudentId(Integer studentId);
}

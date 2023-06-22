package com.student.server.repositories;

import com.student.server.models.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentInfoRepository extends JpaRepository<StudentInfo, Integer> {
    StudentInfo findByStudentId(Integer studentId);
}

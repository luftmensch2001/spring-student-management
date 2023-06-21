package com.student.server.repositories;

import com.student.server.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByStudentCode(String studentCode);
}

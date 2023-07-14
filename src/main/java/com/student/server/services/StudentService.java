package com.student.server.services;

import com.student.server.models.Student;
import com.student.server.DTO.StudentDTO;
import org.springframework.data.util.Pair;

import java.time.LocalDate;

public interface StudentService {
    public Iterable<StudentDTO> getAllStudents(String studentCode, String studentName, LocalDate dateOfBirth);
    public Pair<Boolean, Object> getStudentById(Integer studentId);
    public Pair<Boolean, Object> createNewStudent(Student student);
    public String generateStudentCode();
    public Pair<Boolean, Object> createNewStudentFullInfo(StudentDTO studentDTO);
    public Pair<Boolean, Object> updateStudent(Integer studentId, StudentDTO studentDTO);
    public Pair<Boolean, Object> deleteStudent(Integer studentId);
}

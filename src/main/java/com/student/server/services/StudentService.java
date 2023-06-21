package com.student.server.services;

import com.student.server.models.ResponseObject;
import com.student.server.models.Student;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    public Iterable<Student> getAllStudents();
    public ResponseEntity<ResponseObject> createNewStudent(Student student);
    public String generateStudentCode();
}

package com.student.server.services;

import com.student.server.models.ResponseObject;
import com.student.server.models.Student;
import com.student.server.models.StudentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface StudentService {
    public Iterable<StudentDTO> getAllStudents();
    public ResponseEntity<ResponseObject> createNewStudent(Student student);
    public ResponseEntity<ResponseObject> generateStudentCode();
    public ResponseEntity<ResponseObject> createNewStudentFullInfo(StudentDTO studentDTO);
    public ResponseEntity<ResponseObject> updateStudent(StudentDTO studentDTO);
}

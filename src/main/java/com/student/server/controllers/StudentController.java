package com.student.server.controllers;

import com.student.server.models.ResponseObject;
import com.student.server.models.Student;
import com.student.server.models.StudentDTO;
import com.student.server.models.StudentInfo;
import com.student.server.repositories.StudentRepository;
import com.student.server.services.StudentInfoService;
import com.student.server.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentService studentService;
    @Autowired
    StudentInfoService studentInfoService;

//    Get all
    @GetMapping("/getAll")
    public Iterable<Student> getAll() {
        return studentRepository.findAll();
    }

//    Get generated student code
    @GetMapping("/generatedCode")
    public ResponseEntity<ResponseObject> genarateStudentCode() {
        return studentService.generateStudentCode();
    }

//    Create new student with full info
    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createNewStudentFullInfo(@RequestBody StudentDTO studentDTO) {
        return studentService.createNewStudentFullInfo(studentDTO);
    }
}

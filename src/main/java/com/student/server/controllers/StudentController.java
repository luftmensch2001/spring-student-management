package com.student.server.controllers;

import com.student.server.models.ResponseObject;
import com.student.server.models.Student;
import com.student.server.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

//    Get all
    @GetMapping("/getAll")
    public Iterable<Student> getAll() {
        return studentRepository.findAll();
    }

//    Create new student
    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createNewStudent(@RequestBody Map<String, String> requestBody) {
        return null;
    }
}

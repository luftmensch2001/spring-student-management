package com.student.server.controllers;

import com.student.server.DTO.StudentDTO;
import com.student.server.repositories.StudentRepository;
import com.student.server.services.StudentInfoService;
import com.student.server.services.StudentService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/students")
@CrossOrigin (origins = "http://localhost:8080" , exposedHeaders = "Authorization")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentService studentService;
    @Autowired
    StudentInfoService studentInfoService;

//    Get all students
    @GetMapping("/")
    public ResponseEntity<?> getAll(@RequestParam @Nullable String studentCode,
                                       @RequestParam @Nullable String studentName,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Nullable LocalDate dateOfBirth) {
        return ResponseEntity.ok()
                .body(studentService.getAllStudents(studentCode, studentName, dateOfBirth));
    }

//    Get generated student code
    @GetMapping("/generatedCode")
    public ResponseEntity<?> genarateStudentCode() {
        return ResponseEntity.ok()
                .body(studentService.generateStudentCode());
    }

//    Create new student with full info
    @PostMapping("/create")
    public ResponseEntity<?> createNewStudentFullInfo(@RequestBody StudentDTO studentDTO) {
        Pair<Boolean, Object> result = studentService.createNewStudentFullInfo(studentDTO);
        if (result.getFirst()) {
            return ResponseEntity.ok().body(result.getSecond());
        } else {
            return ResponseEntity.badRequest().body(result.getSecond());
        }
    }
//    Update student
    @PutMapping("/update/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable Integer studentId, @RequestBody StudentDTO studentDTO) {
        Pair<Boolean, Object> result = studentService.updateStudent(studentId, studentDTO);
        if (result.getFirst()) {
            return ResponseEntity.ok().body(result.getSecond());
        } else {
            return ResponseEntity.badRequest().body(result.getSecond());
        }
    }
//    Delete student
    @DeleteMapping("/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer studentId) {
        Pair<Boolean, Object> result = studentService.deleteStudent(studentId);
        if (result.getFirst()) {
            return ResponseEntity.ok().body(result.getSecond());
        } else {
            return ResponseEntity.badRequest().body(result.getSecond());
        }
    }
}

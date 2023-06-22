package com.student.server.services;

import com.student.server.models.ResponseObject;
import com.student.server.models.Student;
import com.student.server.models.StudentDTO;
import com.student.server.models.StudentInfo;
import com.student.server.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentInfoService studentInfoService;

//    List all students
    @Override
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    };

//    Create new student
    @Override
    public ResponseEntity<ResponseObject> createNewStudent(Student student) {
//    Check valid input
        ResponseEntity<ResponseObject> res = checkValidInput(student);
        if (res != null && res.getBody().getStatus() == "FAILED") {
            return res;
        }
//        Create new student
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Create student successfully", studentRepository.save(student))
        );
    }
//    Create new student full info
    @Override
    public ResponseEntity<ResponseObject> createNewStudentFullInfo(StudentDTO studentDTO) {
//        Add student
        Student student = new Student(studentDTO.getStudentName(), studentDTO.getStudentCode());
        ResponseEntity<ResponseObject> res = createNewStudent(student);
//        Check add student successfully ?
        if (res.getBody().getMessage().equals("FAILED")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ResponseObject("FAILED", "Create student failed")
            );
        }
//        Add studentInfo
        Student newStudent = (Student) res.getBody().getObject();
        StudentInfo studentInfo = new StudentInfo(newStudent.getStudentId(), studentDTO.getAddress(), studentDTO.getAverageScore(), studentDTO.getDateOfBirth());
        return studentInfoService.createNewStudentInfo(studentInfo);
    }

//    Generate Student code
    @Override
    public ResponseEntity<ResponseObject> generateStudentCode() {
//        Generate 8 chars code format: STUxxxxx
        while (true) {
            // Generate random int value from 1 to 99999
            int randomInt = (int)Math.floor(Math.random() * (99998) + 1);
            String code = "STU" + Integer.toString(randomInt);
//                Insert 0 before code number
            while (code.length() < 8) {
                code = code.substring(0, 3) + '0' + code.substring(3);
            }
//            Check code existing ?
            boolean existedCode = (studentRepository.findByStudentCode(code) != null);
            if (!existedCode)
                return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", code)
                );
        }
    }
    public ResponseEntity<ResponseObject> checkValidInput(Student student) {
//        Check required
        if (student.getStudentCode().length() == 0 || student.getStudentName().length() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FAILED", "Missing information")
            );
        }
//        Check length
        if (student.getStudentName().length() > 20) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ResponseObject("FAILED", "Student name length must be <= 20 characters")
            );
        }
        return null;
    };
}

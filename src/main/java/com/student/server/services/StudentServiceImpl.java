package com.student.server.services;

import com.student.server.models.ResponseObject;
import com.student.server.models.Student;
import com.student.server.models.StudentDTO;
import com.student.server.models.StudentInfo;
import com.student.server.repositories.StudentInfoRepository;
import com.student.server.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentInfoService studentInfoService;
    @Autowired
    private StudentInfoRepository studentInfoRepository;

//    List all students
    @Override
    public Iterable<StudentDTO> getAllStudents() {
        List<StudentDTO> responseList = new ArrayList<>();
        List<Student> listStudent  = studentRepository.findAll();
        listStudent.forEach(student ->{
            StudentInfo studentInfo = studentInfoRepository.findByStudentId(student.getStudentId());
            StudentDTO responseItem = new StudentDTO(
                    student.getStudentId(),
                    student.getStudentName(),
                    student.getStudentCode(),
                    studentInfo.getInfoId(),
                    studentInfo.getAddress(),
                    studentInfo.getAverageScore(),
                    studentInfo.getDateOfBirth());
            responseList.add(responseItem);
        });
        return responseList;
    }

//    Create new student
    @Override
    public ResponseEntity<ResponseObject> createNewStudent(Student student) {
//    Check valid input
        ResponseEntity<ResponseObject> res = checkValidInput(student);
        if (res != null && res.getBody().getStatus().equals("FAILED")) {
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
        if (res.getBody().getStatus().equals("FAILED")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ResponseObject("FAILED", "Create student failed")
            );
        }
//        Add studentInfo
        Student newStudent = (Student) res.getBody().getObject();
        StudentInfo studentInfo = new StudentInfo(newStudent.getStudentId(), studentDTO.getAddress(), studentDTO.getAverageScore(), studentDTO.getDateOfBirth());
        return studentInfoService.createNewStudentInfo(studentInfo);
    }

//    Update student
    @Override
    public ResponseEntity<ResponseObject> updateStudent(StudentDTO studentDTO) {
//        Update student table
        Optional<Student> updatedStudent = studentRepository.findById(studentDTO.getStudentId())
                .map(student -> {
                    student.setStudentName(studentDTO.getStudentName());
                    return studentRepository.save(student);
                });
        if (!updatedStudent.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("FAILED", "Student not found")
            );
        }
//        Update student_info table
        int infoId = studentInfoRepository.findByStudentId(studentDTO.getStudentId()).getInfoId();
        Optional<StudentInfo> updatedStudentInfo = studentInfoRepository.findById(infoId)
                .map(studentInfo -> {
                    studentInfo.setAddress(studentDTO.getAddress());
                    studentInfo.setAverageScore(studentDTO.getAverageScore());
                    studentInfo.setDateOfBirth(studentDTO.getDateOfBirth());
                    return studentInfoRepository.save(studentInfo);
                });
//        Check success ?
        return (updatedStudent.isPresent() && updatedStudentInfo.isPresent()) ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Updated student")
            ):
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("FAILED", "Failed to update student")
            );
    }

    @Override
    public ResponseEntity<ResponseObject> deleteStudent(Integer studentId) {
        StudentInfo foundStudentInfo = studentInfoRepository.findByStudentId(studentId);
        if (foundStudentInfo != null) {
            int infoId = foundStudentInfo.getInfoId();
            studentInfoRepository.deleteById(infoId);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FAILED", "Student not fount")
            );
        }

        studentRepository.deleteById(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Deleted student")
        );
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

//    Validate input
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
    }
}

package com.student.server.servicesImpl;

import com.student.server.models.Student;
import com.student.server.DTO.StudentDTO;
import com.student.server.models.StudentInfo;
import com.student.server.repositories.StudentInfoRepository;
import com.student.server.repositories.StudentRepository;
import com.student.server.services.StudentInfoService;
import com.student.server.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public Iterable<StudentDTO> getAllStudents(String studentCode, String studentName, LocalDate dateOfBirth) {
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
//        Filter by studentCode
        if (studentCode != null && studentCode.trim().length() > 0) {
            responseList.removeIf(item -> !item.getStudentCode().equals(studentCode));
        }
//        Filter by studentName
        if (studentName != null && studentName.trim().length() > 0) {
            responseList.removeIf(item -> !item.getStudentName().equals(studentName));
        }
//        Filter by studentCode
        if (dateOfBirth != null) {
            responseList.removeIf(item -> item.getDateOfBirth() == null || item.getDateOfBirth().compareTo(dateOfBirth) != 0);
        }
        return responseList;
    }

//    Create new student
    @Override
    public Pair<Boolean, Object> createNewStudent(Student student) {
//        Check valid input
        Pair<Boolean, Object> res = checkValidInput(student);
        if (res.getFirst() == false) {
            return res; // return failed with message
        }
//        Create new student
        return Pair.of(true, studentRepository.save(student));
    }

//    Create new student full info
    @Override
    public Pair<Boolean, Object> createNewStudentFullInfo(StudentDTO studentDTO) {
//        Add student
        Student student = new Student(studentDTO.getStudentName(), studentDTO.getStudentCode());
        Pair<Boolean, Object> res = createNewStudent(student);
//        Check add student successfully ?
        if (res.getFirst() == false) {
            return res; // return failed with message
        }
//        Add studentInfo
        Student newStudent = (Student) res.getSecond();
        StudentInfo studentInfo = new StudentInfo(newStudent.getStudentId(), studentDTO.getAddress(), studentDTO.getAverageScore(), studentDTO.getDateOfBirth());
        return studentInfoService.createNewStudentInfo(studentInfo);
    }

//    Update student
    @Override
    public Pair<Boolean, Object> updateStudent(Integer studentId, StudentDTO studentDTO) {
//        Update student table
        Optional<Student> updatedStudent = studentRepository.findById(studentId)
                .map(student -> {
                    student.setStudentName(studentDTO.getStudentName());
                    return studentRepository.save(student);
                });
        if (!updatedStudent.isPresent()) {
            return Pair.of(false, "Student not found");
        }

//        Update student_info table
        int infoId = studentInfoRepository.findByStudentId(studentId).getInfoId();
        Optional<StudentInfo> updatedStudentInfo = studentInfoRepository.findById(infoId)
                .map(studentInfo -> {
                    studentInfo.setAddress(studentDTO.getAddress());
                    studentInfo.setAverageScore(studentDTO.getAverageScore());
                    studentInfo.setDateOfBirth(studentDTO.getDateOfBirth());
                    return studentInfoRepository.save(studentInfo);
                });
//        Check success ?
        return (updatedStudent.isPresent() && updatedStudentInfo.isPresent()) ?
                Pair.of(true, "Updated student") :
                Pair.of(false, "Failed to update student");
    }

    @Override
    public Pair<Boolean, Object> deleteStudent(Integer studentId) {
        StudentInfo foundStudentInfo = studentInfoRepository.findByStudentId(studentId);
        if (foundStudentInfo != null) {
            int infoId = foundStudentInfo.getInfoId();
            studentInfoRepository.deleteById(infoId);
            studentRepository.deleteById(studentId);
            return Pair.of(true, "Deleted student");
        } else {
            return Pair.of(false, "Student not found");
        }
    }

    //    Generate Student code
    @Override
    public String generateStudentCode() {
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
                return code;
        }
    }

//    Validate input
    public Pair<Boolean, Object> checkValidInput(Student student) {
//        Check required
        if (student.getStudentCode().length() == 0 || student.getStudentName().length() == 0) {
            return Pair.of(false, "Missing information");
        }
//        Check length
        if (student.getStudentName().length() > 20) {
            return Pair.of(false, "Student name length must be <= 20 characters");
        }
        return Pair.of(true, "OK"); // valid input
    }
}

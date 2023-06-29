package com.student.server.repositories;

import com.student.server.models.Student;
import com.student.server.models.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {
    private static final String HASH_KEY = "Student";
    @Autowired
    private RedisTemplate template;
     public Student findByStudentCode(String studentCode) {
         List<Student> list = findAll();
         for (int i = 0; i<list.size(); i++) {
             if (list.get(i).getStudentCode().equals(studentCode)) {
                 return list.get(i);
             }
         }
         return null;
     };

//     List all student
    public List<Student> findAll() {
        return template.opsForHash().values(HASH_KEY);
    }

//    Create new student
    public Object save(Student student) {
        List<Student> listStudent = findAll();
//        Find current max id
        int maxId = 0;
        if (listStudent.size() > 0)
            maxId = listStudent.stream().max(Comparator.comparing(Student::getStudentId)).get().getStudentId();
//        Increase id for new student
        student.setStudentId(maxId + 1);
//        Save to cache
        template.opsForHash().put(HASH_KEY, maxId + 1, student);
        return student;
    }

//    Delete student by id
    public void deleteById(Integer studentId) {
        template.opsForHash().delete(HASH_KEY, studentId);
    }

//    Update student
    public boolean update(StudentDTO studentDTO) {
//        Check student existing ?
        Student foundStudent = (Student) template.opsForHash().get(HASH_KEY, studentDTO.getStudentId());
        if (foundStudent == null) return false;
//        Update student
        foundStudent.setStudentName(studentDTO.getStudentName());
        template.opsForHash().put(HASH_KEY, foundStudent.getStudentId(), foundStudent);
        return true;
    }
}

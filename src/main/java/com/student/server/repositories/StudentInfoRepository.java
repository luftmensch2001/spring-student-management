package com.student.server.repositories;

import com.student.server.models.Student;
import com.student.server.models.StudentDTO;
import com.student.server.models.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Repository
public class StudentInfoRepository {
    private static final String HASH_KEY = "StudentInfo";
    @Autowired
    private RedisTemplate template;
    @Autowired
    private StudentRepository studentRepository;

//    Find info by studentId
    public StudentInfo findByStudentId(Integer studentId) {
        return (StudentInfo) template.opsForHash().get(HASH_KEY, studentId);
    }

//    List all info
    public List<StudentInfo> findAll() {
        return template.opsForHash().values(HASH_KEY);
    }

//    Create new info
    public Object save(StudentInfo studentInfo) {
        List<StudentInfo> listStudentInfo = findAll();
//        Find current max id
        int maxId = 0;
        if (listStudentInfo.size() > 0)
            maxId = listStudentInfo.stream().max(Comparator.comparing(StudentInfo::getInfoId)).get().getInfoId();
//        Increase id for new student
        studentInfo.setInfoId(maxId + 1);
//        Save to cache
        template.opsForHash().put(HASH_KEY, maxId + 1, studentInfo);
        return studentInfo;
    }

//    Delete info
    public void deleteById(int infoId) {
        template.opsForHash().delete(HASH_KEY, infoId);
    }

//    Update info
    public boolean update(StudentDTO studentDTO) {
//        Check student existing ?
        StudentInfo foundStudentInfo = findByStudentId(studentDTO.getStudentId());
        if (foundStudentInfo == null) return false;
//        Update student
        foundStudentInfo.setAddress(studentDTO.getAddress());
        foundStudentInfo.setAverageScore(studentDTO.getAverageScore());
        foundStudentInfo.setDateOfBirth(studentDTO.getDateOfBirth());
        template.opsForHash().put(HASH_KEY, foundStudentInfo.getInfoId(), foundStudentInfo);
        return true;
    }
}

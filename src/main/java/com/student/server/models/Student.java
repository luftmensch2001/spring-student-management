package com.student.server.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Entity
@Data
@RedisHash("Student")
public class Student implements Serializable {
    @Id
    @Indexed
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int studentId;
    @Indexed
    String studentName;
    @Indexed
    String studentCode;

    public Student() {
    }

    public Student(int studentId, String studentName, String studentCode) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentCode = studentCode;
    }

    public Student(String studentName, String studentCode) {
        this.studentName = studentName;
        this.studentCode = studentCode;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }
}

package com.student.server.models;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int studentId;
    @Column(name = "student_name")
    String studentName;
    @Column(name = "student_code")
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

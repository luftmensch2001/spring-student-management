package com.student.server.models;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "student_info")
public class StudentInfo {
    @Id
    @Column(name = "info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int infoId;
    @Column(name = "student_id")
    int studentId;
    @Column(name= "address")
    String address;
    @Column(name= "average_score")
    Double averageScore;
    @Column(name= "date_of_birth")
    LocalDate dateOfBirth;

    public StudentInfo() {
    }

    public StudentInfo(int infoId, int studentId, String address, Double averageScore, LocalDate dateOfBirth) {
        this.infoId = infoId;
        this.studentId = studentId;
        this.address = address;
        this.averageScore = averageScore;
        this.dateOfBirth = dateOfBirth;
    }

    public StudentInfo(int studentId, String address, Double averageScore, LocalDate dateOfBirth) {
        this.studentId = studentId;
        this.address = address;
        this.averageScore = averageScore;
        this.dateOfBirth = dateOfBirth;
    }

    public int getInfoId() {
        return infoId;
    }

    public void setInfoId(int infoId) {
        this.infoId = infoId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

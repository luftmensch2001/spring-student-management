package com.student.server.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@RedisHash("StudentInfo")
public class StudentInfo implements Serializable {
    @Id
    @Indexed
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int infoId;
    @Indexed
    int studentId;
    @Indexed
    String address;
    @Indexed
    Double averageScore;
    @Indexed
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

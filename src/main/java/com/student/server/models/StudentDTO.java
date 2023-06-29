package com.student.server.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class StudentDTO implements Serializable {
    @Id
    @Indexed
    int studentId;
    @Indexed
    String studentName;
    @Indexed
    String studentCode;
    @Indexed
    int infoId;
    @Indexed
    String address;
    @Indexed
    Double averageScore;
    @Indexed
    LocalDate dateOfBirth;

    public StudentDTO(int studentId, String studentName, String studentCode, int infoId, String address, Double averageScore, LocalDate dateOfBirth) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentCode = studentCode;
        this.infoId = infoId;
        this.address = address;
        this.averageScore = averageScore;
        this.dateOfBirth = dateOfBirth;
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

    public int getInfoId() {
        return infoId;
    }

    public void setInfoId(int infoId) {
        this.infoId = infoId;
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

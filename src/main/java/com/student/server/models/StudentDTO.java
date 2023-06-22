package com.student.server.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "student")
@SecondaryTable(name = "student_info")
public class StudentDTO {
    @Id
    @Column(name = "student_id")
    int studentId;
    @Column(name = "student_name")
    String studentName;
    @Column(name = "student_code")
    String studentCode;
    @Column(name = "info_id")
    int infoId;
    @Column(name= "address")
    String address;
    @Column(name= "average_scrore")
    Double averageScore;
    @Column(name= "date_of_birth")
    Date dateOfBirth;

    public StudentDTO(int studentId, String studentName, String studentCode, int infoId, String address, Double averageScore, Date dateOfBirth) {
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

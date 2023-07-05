package com.student.server.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public StudentInfo(int studentId, String address, Double averageScore, LocalDate dateOfBirth) {
        this.studentId = studentId;
        this.address = address;
        this.averageScore = averageScore;
        this.dateOfBirth = dateOfBirth;
    }
}

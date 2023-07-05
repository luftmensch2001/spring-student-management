package com.student.server.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @Column(name= "average_score")
    Double averageScore;
    @Column(name= "date_of_birth")
    LocalDate dateOfBirth;
}

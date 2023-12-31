package com.student.server.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public Student(String studentName, String studentCode) {
        this.studentName = studentName;
        this.studentCode = studentCode;
    }
}

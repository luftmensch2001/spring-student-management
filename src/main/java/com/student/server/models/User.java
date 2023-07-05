package com.student.server.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userId;
    @Column(name = "user_name")
    String userName;
    @Column(name = "password")
    String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}

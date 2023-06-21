package com.student.server.controllers;

import com.student.server.models.ResponseObject;
import com.student.server.models.User;
import com.student.server.repositories.UserRepository;
import com.student.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

//    Register
    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@RequestBody User user) {
        return userService.createNewUser(user);
    }
//    Login
    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody User user) {
        return userService.checkLogin(user);
    }
}

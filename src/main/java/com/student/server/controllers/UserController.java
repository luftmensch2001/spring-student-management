package com.student.server.controllers;

import com.student.server.models.User;
import com.student.server.repositories.UserRepository;
import com.student.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

//    Register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        Pair<Boolean, Object> result = userService.createNewUser(user);
        return ResponseEntity.ok().body(result.getSecond());

    }
//    Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Pair<Boolean, Object> result = userService.checkLogin(user);
        return ResponseEntity.ok().body(result.getSecond());
    }
}

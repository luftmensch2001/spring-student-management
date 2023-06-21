package com.student.server.services;

import com.student.server.models.ResponseObject;
import com.student.server.models.User;
import com.student.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
//    Register Processing
    @Override
    public ResponseEntity<ResponseObject> createNewUser(User user) {
//        Check valid input
        ResponseEntity<ResponseObject> res = checkRequiedAndLength(user);
        if (res != null && res.getBody().getStatus() == "FAILED") {
            return res;
        }
//        Check if userName existed
        if (userRepository.findByUserName(user.getUserName()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ResponseObject("FAILED", "User Name is already existed")
            );
        };
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Register successfully", userRepository.save(user))
        );
    };

//    Login Processing
    @Override
    public ResponseEntity<ResponseObject> checkLogin(User user) {
//    Check valid input
        ResponseEntity<ResponseObject> res = checkRequiedAndLength(user);
        if (res != null && res.getBody().getStatus() == "FAILED") {
            return res;
        }
//        Check Login
        User foundUser = userRepository.findByUserName(user.getUserName());
//        Check user existing ?
        if (foundUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FAILED", "User Name not found")
            );
        }
//        Check password
        if (foundUser.getPassword().equals(user.getPassword())) {
//            Password is correct
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Login successfully")
            );
        } else {
//            Password is incorrect
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ResponseObject("FAILED", "Password is incorrect")
            );
        }
    };

//    Check valid input
    public ResponseEntity<ResponseObject> checkRequiedAndLength(User user) {
//        Check required
        if (user.getUserName().length() == 0 || user.getPassword().length() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FAILED", "Missing information")
            );
        }
//        Check length userName
        if (user.getUserName().length() > 20) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ResponseObject("FAILED", "User Name length must be < 20 characters")
            );
        }
//        Check length password
        if (user.getPassword().length() > 15) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ResponseObject("FAILED", "Password length must be < 15 characters")
            );
        }
        return null;
    }
}

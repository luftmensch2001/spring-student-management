package com.student.server.services;

import com.student.server.models.ResponseObject;
import com.student.server.models.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public ResponseEntity<ResponseObject> createNewUser(User user);
    public ResponseEntity<ResponseObject> checkLogin(User user);
}

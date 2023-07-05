package com.student.server.services;

import com.student.server.models.User;
import org.springframework.data.util.Pair;

public interface UserService {
    public Pair<Boolean, Object> createNewUser(User user);
    public Pair<Boolean, Object> checkLogin(User user);
}

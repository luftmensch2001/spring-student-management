package com.student.server.servicesImpl;

import com.student.server.models.User;
import com.student.server.repositories.UserRepository;
import com.student.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
//    Register Processing
    @Override
    public Pair<Boolean, Object> createNewUser(User user) {
//        Check valid input
        Pair<Boolean, Object> res = checkRequiedAndLength(user);
        if (res.getFirst() == false) {
            return res;
        }
//        Check if userName existed
        if (userRepository.findByUserName(user.getUserName()) != null) {
            return Pair.of(false, "User Name is already existed");
        } else {
            return Pair.of(true, userRepository.save(user));
        }
    };

//    Login Processing
    @Override
    public Pair<Boolean, Object> checkLogin(User user) {
//    Check valid input
        Pair<Boolean, Object> res = checkRequiedAndLength(user);
        if (res.getFirst() == false) {
            return res;
        }
//        Check Login
        User foundUser = userRepository.findByUserName(user.getUserName());
//        Check user existing ?
        if (foundUser == null) {
            return Pair.of(false, "User Name not found");
        }
//        Check password
        if (foundUser.getPassword().equals(user.getPassword())) {
//            Password is correct
            return Pair.of(true, "Login successfully");
        } else {
//            Password is incorrect
            return Pair.of(true, "Password is incorrect");
        }
    };

//    Check valid input
    public Pair<Boolean, Object> checkRequiedAndLength(User user) {
//        Check required
        if (user.getUserName().length() == 0 || user.getPassword().length() == 0) {
            return Pair.of(false, "Missing information");
        }
//        Check length userName
        if (user.getUserName().length() > 20) {
            return Pair.of(false, "User Name length must be <= 20 characters");
        }
//        Check length password
        if (user.getPassword().length() < 6 || user.getPassword().length() > 15) {
            return Pair.of(false, "Password length must be from 6 to 15 characters");
        }
//        Valid input
        return Pair.of(true, "OK");
    }
}

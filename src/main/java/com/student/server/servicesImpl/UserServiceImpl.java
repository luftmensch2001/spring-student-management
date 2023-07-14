package com.student.server.servicesImpl;

import com.student.server.DTO.AuthenticationDTO;
import com.student.server.models.Role;
import com.student.server.models.User;
import com.student.server.repositories.UserRepository;
import com.student.server.security.jwt.JwtService;
import com.student.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;

//    Register Processing
    @Override
    public Pair<Boolean, Object> createNewUser(User user) {
//        Check valid input
        Pair<Boolean, Object> res = checkRequiedAndLength(user);
        if (!res.getFirst()) {
            return res;
        }
//        Check if userName existed
        if (userRepository.findByUserName(user.getUsername()).isPresent()) {
            return Pair.of(false, "Username " + user.getUsername() + " is already existed");
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(Role.ADMIN);
            User createdUser = userRepository.save(user);
            String token = jwtService.generateToken(user);
            AuthenticationDTO response = new AuthenticationDTO(createdUser, token);
            return Pair.of(true, response);
        }
    };

//    Login Processing
    @Override
    public Pair<Boolean, Object> checkLogin(User user) {
//    Check valid input
        Pair<Boolean, Object> res = checkRequiedAndLength(user);
        if (!res.getFirst()) {
            return res;
        }
//        Check Login
        Optional<User> foundUser = userRepository.findByUserName(user.getUsername());
//        Check user existing ?
        if (!foundUser.isPresent()) {
            return Pair.of(false, "User " + user.getUsername() + " not found");
        }
//        Check authentication
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        var jwtToken = jwtService.generateToken(foundUser.get());
        AuthenticationDTO response = new AuthenticationDTO(foundUser.get(), jwtToken);
        return Pair.of(true, response);
    };

//    Check valid input
    public Pair<Boolean, Object> checkRequiedAndLength(User user) {
//        Check required
        if (user.getUsername().length() == 0 || user.getPassword().length() == 0) {
            return Pair.of(false, "Missing information");
        }
//        Check length userName
        if (user.getUsername().length() > 20) {
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

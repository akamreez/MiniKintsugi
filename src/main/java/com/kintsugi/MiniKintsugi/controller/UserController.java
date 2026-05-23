package com.kintsugi.MiniKintsugi.controller;

import com.kintsugi.MiniKintsugi.service.UserService;
import com.kintsugi.MiniKintsugi.user.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userRepository , PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(
            @RequestBody User user) {


        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );
        User savedUser = userRepository.registerUser(user);

        return new ResponseEntity<>(
                savedUser,
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(
            @RequestBody User user
    ) {

        boolean isAuthenticated =
                userRepository.loginUser(
                        user.getUsername(),
                        user.getPassword()
                );

        if(isAuthenticated) {
            return ResponseEntity.ok("Login Successful");
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Invalid Username or Password");
    }
}
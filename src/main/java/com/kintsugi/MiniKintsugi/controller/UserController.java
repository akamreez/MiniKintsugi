package com.kintsugi.MiniKintsugi.controller;

import com.kintsugi.MiniKintsugi.dto.UserRequestDTO;
import com.kintsugi.MiniKintsugi.dto.UserResponseDTO;
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
    public ResponseEntity<UserResponseDTO> registerUser(
            @RequestBody UserRequestDTO dto) {

        // the parameters of the methods are the clients request data always.

        User user = new User();

        user.setUsername(dto.getUsername());

        user.setPassword(dto.getPassword());

        User savedUser =
                userRepository.registerUser(user);

        UserResponseDTO responseDTO =
                new UserResponseDTO();

        responseDTO.setId(savedUser.getId());

        responseDTO.setUsername(
                savedUser.getUsername()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(
            @RequestBody UserRequestDTO dto
    ) {


        boolean isAuthenticated =
                userRepository.loginUser(
                        dto.getUsername(),
                        dto.getPassword()
                );

        if(isAuthenticated) {
            return ResponseEntity.ok("Login Successful");
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Invalid Username or Password");
    }
}
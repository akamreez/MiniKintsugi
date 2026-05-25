package com.kintsugi.MiniKintsugi.service;

import com.kintsugi.MiniKintsugi.repository.UserRepository;
import com.kintsugi.MiniKintsugi.user.User;
import com.kintsugi.MiniKintsugi.exception.UserAlreadyExistsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {

        if(userRepository.existsByUsername(
                user.getUsername()
        )) {
            throw new UserAlreadyExistsException(
                    "Username already exists"
            );
        }

        return userRepository.save(user);
    }

    public boolean loginUser(
            String username,
            String password
    ) {

        User existingUser =
                userRepository.findByUsername(username);

        if(existingUser == null) {
            return false;
        }


        return passwordEncoder.matches(
                password,
                existingUser.getPassword()
        );
    }


}
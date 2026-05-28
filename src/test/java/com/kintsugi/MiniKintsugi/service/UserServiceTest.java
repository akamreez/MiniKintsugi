package com.kintsugi.MiniKintsugi.service;

// custom exception we created
import com.kintsugi.MiniKintsugi.exception.UserAlreadyExistsException;
import static org.junit.jupiter.api.Assertions.assertEquals;
// repository dependency
import com.kintsugi.MiniKintsugi.repository.UserRepository;

// User entity
import com.kintsugi.MiniKintsugi.user.User;

// JUnit test annotation
import org.junit.jupiter.api.Test;

// Mockito annotations
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;

// dependency used in UserService
import org.springframework.security.crypto.password.PasswordEncoder;

// JUnit assertion
import static org.junit.jupiter.api.Assertions.assertThrows;

// Mockito fake behavior setup
import static org.mockito.Mockito.when;

public class UserServiceTest {

    // fake repository object
    @Mock
    private UserRepository userRepository;

    // fake password encoder object
    @Mock
    private PasswordEncoder passwordEncoder;

    // create UserService and inject fake dependencies
    @InjectMocks
    private UserService userService;

    // initialize mocks
    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    // marks this method as a test
    @Test
    void shouldThrowExceptionWhenUsernameExists() {
/*

based on the expected behaviour of a logic , we give a method name ,
and inside that method we assume the expected behaviour happens and write logic for it ,

* */
        // create fake user object
        User user = new User();

        user.setUsername("az");

        user.setPassword("123");

        // fake repository behavior
        // whenever existsByUsername("az") is called,
        // return true
        when(
                userRepository.existsByUsername("az")
        ).thenReturn(true);

        // verify exception is thrown
        assertThrows(
                UserAlreadyExistsException.class,

                // code expected to throw exception
                () -> userService.registerUser(user)
        );
    }

    @Test
    void shouldRegisterUserSuccessfully() {

        User user = new User();

        user.setUsername("newuser");

        user.setPassword("123");

        // simulate username does NOT exist
        when(
                userRepository.existsByUsername(
                        "newuser"
                )
        ).thenReturn(false);

        // simulate password hashing
        when(
                passwordEncoder.encode("123")
        ).thenReturn("hashedPassword");

        // simulate database save
        when(
                userRepository.save(user)
        ).thenReturn(user);

        User savedUser =
                userService.registerUser(user);

        assertEquals(
                "newuser",
                savedUser.getUsername()
        );

        verify(userRepository).save(user);
    }



}

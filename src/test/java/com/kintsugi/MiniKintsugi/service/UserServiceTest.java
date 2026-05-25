package com.kintsugi.MiniKintsugi.service;

// custom exception we created
import com.kintsugi.MiniKintsugi.exception.UserAlreadyExistsException;

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
}

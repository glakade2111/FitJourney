package com.example.service;

import com.example.Entity.User;
import com.example.Repository.UserRepository;
import com.example.Service.UserService;
import com.example.Service.WorkoutService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    WorkoutService workoutService;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void allUser()
    {

        User user=new User();
        user.setId(11);
        user.setName("ganesh lakade");

        User user1=new User();
        user1.setId(12);
        user1.setName("joe andersone");

        Arrays.asList()
    }
}

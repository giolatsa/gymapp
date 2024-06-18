package com.example.gymapp.service;

import com.example.gymapp.model.User;
import com.example.gymapp.repo.UserDao;
import com.example.gymapp.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        userService.createUser(user);

        verify(userDao, times(1)).create(user);
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        userService.createUser(user);
        user.setLastName("Smith");
        userService.updateUser(user);

        verify(userDao, times(1)).update(user);
    }

    @Test
    void testGetUser() {
        User user = new User();
        when(userDao.select(1L)).thenReturn(user);

        User fetchedUser = userService.getUser(1L);
        assertNotNull(fetchedUser);
        verify(userDao, times(1)).select(1L);
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User();
        User user2 = new User();
        when(userDao.selectAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
        verify(userDao, times(1)).selectAll();
    }
}
package com.example.gymapp.dao;// UserDaoImplTest.java

import com.example.gymapp.TestConfig;
import com.example.gymapp.model.User;
import com.example.gymapp.repo.InMemoryStorage;
import com.example.gymapp.repo.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private InMemoryStorage storage;

    @BeforeEach
    void setUp() {
        storage.clear();
    }

    @Test
    void testCreateAndSelect() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("john.doe");
        user.setPassword("password");

        userDao.create(user);
        User fetchedUser = userDao.select(user.getId());

        assertNotNull(fetchedUser);
        assertEquals("John", fetchedUser.getFirstName());
        assertEquals("Doe", fetchedUser.getLastName());
    }

    @Test
    void testUpdate() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("john.doe");
        user.setPassword("password");

        userDao.create(user);
        user.setLastName("Smith");
        userDao.update(user);
        User fetchedUser = userDao.select(user.getId());

        assertNotNull(fetchedUser);
        assertEquals("Smith", fetchedUser.getLastName());
    }

    @Test
    void testSelectAll() {
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setUsername("john.doe");
        user1.setPassword("password");

        User user2 = new User();
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setUsername("jane.doe");
        user2.setPassword("password");

        userDao.create(user1);
        userDao.create(user2);

        List<User> users = userDao.selectAll();
        assertEquals(2, users.size());
    }
}

package com.example.gymapp;

import com.example.gymapp.utils.ProfileUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProfileUtilsTest {

    @Test
    void testGenerateUsername() {
        List<String> existingUsernames = Arrays.asList("john.doe", "john.doe1");

        String username = ProfileUtils.generateUsername("John", "Doe", existingUsernames);
        assertEquals("john.doe2", username);
    }

    @Test
    void testGeneratePassword() {
        String password = ProfileUtils.generatePassword();
        assertNotNull(password);
        assertEquals(10, password.length());
    }
}
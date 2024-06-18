package com.example.gymapp.utils;

import java.security.SecureRandom;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProfileUtils {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final Logger LOGGER = Logger.getLogger(ProfileUtils.class.getName());

    public static String generateUsername(String firstName, String lastName, List<String> existingUsernames) {
        String baseUsername = firstName.toLowerCase() + "." + lastName.toLowerCase();
        String username = baseUsername;
        int serial = 1;

        // Convert existing usernames to lowercase for case-insensitive comparison
        List<String> lowerCaseUsernames = existingUsernames.stream()
                .map(String::toLowerCase).toList();

        while (lowerCaseUsernames.contains(username)) {
            username = baseUsername + serial;
            serial++;
        }

        LOGGER.log(Level.INFO, "Generated username: {0} for firstName: {1}, lastName: {2}", new Object[]{username, firstName, lastName});
        return username;
    }

    public static String generatePassword() {
        StringBuilder password = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            password.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }

        return password.toString();
    }
}

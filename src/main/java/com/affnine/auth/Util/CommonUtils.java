package com.affnine.auth.Util;

import lombok.experimental.UtilityClass;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@UtilityClass
public class CommonUtils {

    private static final SecureRandom random = new SecureRandom();
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static LocalDateTime epochTimeToString(String time){
        // Change to ZoneId.of("Asia/Kolkata") if needed
        return LocalDateTime.ofInstant(
                Instant.ofEpochSecond(Long.parseLong(time)),
                ZoneId.systemDefault() // Change to ZoneId.of("Asia/Kolkata") if needed
        );
    }

    public static Integer generateOTP() {
        return 100_000 + random.nextInt(900_000);
    }

    public static String generateDefaultPassword(int length) {
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return password.toString();
    }
}
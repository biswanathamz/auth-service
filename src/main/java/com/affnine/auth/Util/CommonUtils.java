package com.affnine.auth.Util;

import lombok.experimental.UtilityClass;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@UtilityClass
public class CommonUtils {

    private static final SecureRandom random = new SecureRandom();

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
}
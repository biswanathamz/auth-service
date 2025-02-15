package com.affnine.auth.Util;

import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@UtilityClass
public class CommonUtils {

    public static LocalDateTime epochTimeToString(String time){
        // Change to ZoneId.of("Asia/Kolkata") if needed
        return LocalDateTime.ofInstant(
                Instant.ofEpochSecond(Long.parseLong(time)),
                ZoneId.systemDefault() // Change to ZoneId.of("Asia/Kolkata") if needed
        );
    }
}
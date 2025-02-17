package com.affnine.auth.Service;

public interface RedisService {
    public void set(String key, String value, Long ttl);
    public void setOtpForEmailRegistration(String email, int otp);
}

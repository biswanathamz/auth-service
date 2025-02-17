package com.affnine.auth.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface RedisService {
    public void set(String key, String value, Long ttl);
    public void setOtpForEmailRegistration(String email, long serviceId, int otp) throws JsonProcessingException;
}

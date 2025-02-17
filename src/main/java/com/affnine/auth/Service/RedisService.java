package com.affnine.auth.Service;

import com.affnine.auth.Model.Redis.RegistrationOtpDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RedisService {
    public void set(String key, String value, Long ttl);
    public void deleteKey(String key);
    public void setOtpForEmailRegistration(String email, long serviceId, int otp) throws JsonProcessingException;
    public RegistrationOtpDto getOtpDataForEmailRegistration(String email) throws JsonProcessingException;
    public void deleteOtpForEmailRegistration(String email);
}

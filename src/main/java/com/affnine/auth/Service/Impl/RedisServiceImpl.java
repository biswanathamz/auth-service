package com.affnine.auth.Service.Impl;

import com.affnine.auth.Model.Redis.RegistrationOtpDto;
import com.affnine.auth.Service.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Value("${redis.registration.otp.key-prefix}")
    private String registrationOtpKeyPrefix;

    @Value("${redis.registration.otp.ttl}")
    private Long registrationOtpTtl;

    private final RedisTemplate<String,String> redisTemplate;
    private final ObjectMapper objectMapper;

    public RedisServiceImpl(RedisTemplate<String,String> redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }


    @Override
    public void set(String key, String value, Long ttl) throws IllegalArgumentException {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, ttl, TimeUnit.MINUTES);
    }

    @Override
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void setOtpForEmailRegistration(String email, long serviceId, int otp) throws IllegalArgumentException, JsonProcessingException {
        String key = registrationOtpKeyPrefix + email;
        RegistrationOtpDto registrationOtpDto = RegistrationOtpDto.builder()
                .email(email)
                .applicationSourceId(serviceId)
                .otp(otp)
                .build();
        String jsonValue = objectMapper.writeValueAsString(registrationOtpDto);
        set(key, jsonValue, registrationOtpTtl);
    }

    @Override
    public RegistrationOtpDto getOtpDataForEmailRegistration(String email) throws JsonProcessingException {
        String key = registrationOtpKeyPrefix + email;
        String jsonValue = redisTemplate.opsForValue().get(key);
        if (jsonValue!=null){
            return objectMapper.readValue(jsonValue, RegistrationOtpDto.class);
        }else{
            return null;
        }
    }

    @Override
    public void deleteOtpForEmailRegistration(String email) {
        String key = registrationOtpKeyPrefix + email;
        System.out.println(key);
        deleteKey(key);
    }
}

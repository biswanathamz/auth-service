package com.affnine.auth.Service.Impl;

import com.affnine.auth.Service.RedisService;
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

    public RedisServiceImpl(RedisTemplate<String,String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    public void set(String key, String value, Long ttl) throws IllegalArgumentException {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, ttl, TimeUnit.MINUTES);
    }

    @Override
    public void setOtpForEmailRegistration(String email, int otp) throws IllegalArgumentException {
        String key = registrationOtpKeyPrefix + email;
        set(key, String.valueOf(otp), registrationOtpTtl);
    }
}

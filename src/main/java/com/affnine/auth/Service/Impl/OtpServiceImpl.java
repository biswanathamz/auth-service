package com.affnine.auth.Service.Impl;

import com.affnine.auth.Service.OtpService;
import com.affnine.auth.Service.RedisService;
import com.affnine.auth.Util.CommonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    RedisService redisService;

    @Override
    public void sendOtpToEmail(String email, Integer otp) throws Exception {
        // Send OTP to email
        System.out.println("OTP sent to email: " + email);
    }

    @Override
    public void sendRegisterOtp(String email, Long serviceId) throws Exception, JsonProcessingException {
        int otp = CommonUtils.generateOTP();
        redisService.setOtpForEmailRegistration(email,serviceId, otp);
        sendOtpToEmail(email, otp);
    }
}

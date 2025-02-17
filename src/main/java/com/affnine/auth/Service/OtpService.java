package com.affnine.auth.Service;

public interface OtpService {
    public void sendOtpToEmail(String email, Integer otp) throws Exception;
    public void sendRegisterOtp(String email) throws Exception;
}

package com.affnine.auth.Service.Impl;

import com.affnine.auth.Model.RequestDto.RegisterSendOtpRequestDto;
import com.affnine.auth.Service.AuthService;
import com.affnine.auth.Util.ServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public ResponseEntity<ServiceResponse<String>> sendOtpForRegistration(RegisterSendOtpRequestDto request) {
        // Email
        return null;
    }
}

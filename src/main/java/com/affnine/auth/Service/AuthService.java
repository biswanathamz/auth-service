package com.affnine.auth.Service;

import com.affnine.auth.Model.RequestDto.RegisterSendOtpRequestDto;
import com.affnine.auth.Util.ServiceResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    public ResponseEntity<ServiceResponse<String>> sendOtpForRegistration(RegisterSendOtpRequestDto request);
}

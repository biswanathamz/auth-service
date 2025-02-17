package com.affnine.auth.Controller;

import com.affnine.auth.Controller.Route.AuthServiceRoute;
import com.affnine.auth.Model.RequestDto.RegisterSendOtpRequestDto;
import com.affnine.auth.Model.RequestDto.RegisterVerifyOtpRequestDto;
import com.affnine.auth.Service.AuthService;
import com.affnine.auth.Util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthServiceController implements AuthServiceRoute {

    @Autowired
    AuthService authService;

    @Override
    public ResponseEntity<ServiceResponse<String>> sendOtp(RegisterSendOtpRequestDto request) {
        return authService.sendOtpForRegistration(request);
    }

    @Override
    public ResponseEntity<ServiceResponse<String>> verifyOtp(RegisterVerifyOtpRequestDto request) {
        return authService.verifyOtpForRegistration(request);
    }
}

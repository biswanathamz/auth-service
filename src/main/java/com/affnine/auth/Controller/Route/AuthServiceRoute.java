package com.affnine.auth.Controller.Route;

import com.affnine.auth.Model.RequestDto.RegisterSendOtpRequestDto;
import com.affnine.auth.Util.ServiceResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/v1/auth")
public interface AuthServiceRoute {
    @PostMapping(value = "/register/sendOtp")
    public ResponseEntity<ServiceResponse<String>> sendOtp(@Valid @RequestBody RegisterSendOtpRequestDto request);
}

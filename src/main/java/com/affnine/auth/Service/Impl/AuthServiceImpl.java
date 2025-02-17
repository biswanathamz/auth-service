package com.affnine.auth.Service.Impl;

import com.affnine.auth.Constant.AppConstant;
import com.affnine.auth.Model.RequestDto.RegisterSendOtpRequestDto;
import com.affnine.auth.Model.RequestDto.RegisterVerifyOtpRequestDto;
import com.affnine.auth.Model.User;
import com.affnine.auth.Model.UserRoleService;
import com.affnine.auth.Repository.UserRepository;
import com.affnine.auth.Repository.UserRoleServiceRepository;
import com.affnine.auth.Service.AuthService;
import com.affnine.auth.Service.OtpService;
import com.affnine.auth.Util.ResponseUtils;
import com.affnine.auth.Util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleServiceRepository userRoleServiceRepository;

    @Autowired
    OtpService otpService;

    @Override
    public ResponseEntity<ServiceResponse<String>> sendOtpForRegistration(RegisterSendOtpRequestDto request) {
        try{
            User userData = getUserByEmail(request.getEmail());
            if(userData != null){
                UserRoleService userRoleServiceData = getUserRoleServiceByUserIdAndServiceId(userData.getId(), request.getApplicationSourceId());
                if (userRoleServiceData != null) {
                    return ResponseUtils.badRequest(AppConstant.ERROR_USER_ALREADY_REGISTERED,null);
                }
            }
            otpService.sendRegisterOtp(request.getEmail(), request.getApplicationSourceId());
            return ResponseUtils.successResponse(AppConstant.SUCCESS_OTP_SENT,null);
        }catch (Exception e){
            return ResponseUtils.internalServerError(AppConstant.INTERNAL_SERVER_ERROR_MESSAGE,null);
        }
    }

    @Override
    public ResponseEntity<ServiceResponse<String>> verifyOtpForRegistration(RegisterVerifyOtpRequestDto request) {
        try{
            boolean verifyFlag = otpService.verifyRegisterOtp(request.getEmail(),request.getOtp());
            if(!verifyFlag){
                return ResponseUtils.badRequest(AppConstant.ERROR_INVALID_OTP,null);
            }
            return ResponseUtils.successResponse(AppConstant.SUCCESS_REGISTRATION_COMPLETED,null);
        }catch (Exception e){
            return ResponseUtils.internalServerError(AppConstant.INTERNAL_SERVER_ERROR_MESSAGE,null);
        }
    }

    public User getUserByEmail(String email){
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.orElse(null);
    }

    public UserRoleService getUserRoleServiceByUserIdAndServiceId(Long userId, Long serviceId){
        Optional<UserRoleService> userRoleServiceOptional = userRoleServiceRepository.findByUserIdAndServiceId(userId, serviceId);
        return userRoleServiceOptional.orElse(null);
    }
}

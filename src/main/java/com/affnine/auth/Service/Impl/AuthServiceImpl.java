package com.affnine.auth.Service.Impl;

import com.affnine.auth.Constant.AppConstant;
import com.affnine.auth.Model.*;
import com.affnine.auth.Model.RequestDto.RegisterSendOtpRequestDto;
import com.affnine.auth.Model.RequestDto.RegisterVerifyOtpRequestDto;
import com.affnine.auth.Repository.*;
import com.affnine.auth.Service.AuthService;
import com.affnine.auth.Service.OtpService;
import com.affnine.auth.Util.CommonUtils;
import com.affnine.auth.Util.ResponseUtils;
import com.affnine.auth.Util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.management.relation.Role;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${default.config.role-id}")
    private Long defaultRoleId;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMetaDataRepository userMetaDataRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    UserRoleServiceRepository userRoleServiceRepository;


    @Autowired
    OtpService otpService;

    @Override
    public ResponseEntity<ServiceResponse<String>> sendOtpForRegistration(RegisterSendOtpRequestDto request) {
        try{
            Services services = getServiceById(request.getApplicationSourceId());
            if (services==null) {
                return ResponseUtils.badRequest(AppConstant.ERROR_SERVICE_DETAILS_NOT_FOUND,null);
            }
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
            Services services = getServiceById(request.getApplicationSourceId());
            User newUser;
            if (services==null) {
                return ResponseUtils.badRequest(AppConstant.ERROR_SERVICE_DETAILS_NOT_FOUND,null);
            }

            User userData = getUserByEmail(request.getEmail());
            if(userData != null){
                UserRoleService userRoleServiceData = getUserRoleServiceByUserIdAndServiceId(userData.getId(), request.getApplicationSourceId());
                if (userRoleServiceData != null) {
                    return ResponseUtils.badRequest(AppConstant.ERROR_USER_ALREADY_REGISTERED,null);
                }
            }

            Roles roles = getRoleById(defaultRoleId);

            if(roles==null){
                return ResponseUtils.internalServerError(AppConstant.INTERNAL_SERVER_ERROR_MESSAGE,null);
            }

            boolean verifyFlag = otpService.verifyRegisterOtp(request.getEmail(),request.getOtp());
            if(!verifyFlag){
                return ResponseUtils.badRequest(AppConstant.ERROR_INVALID_OTP,null);
            }

            if(userData==null){
                newUser = User.builder()
                        .firstName(" ")
                        .lastName(" ")
                        .email(request.getEmail())
                        .build();
                System.out.println(newUser);
            }else {
                newUser = userData;
            }
            newUser = userRepository.save(newUser);
            UserMetaData userMetaData = UserMetaData.builder()
                    .user(newUser)
                    .service(services)
                    .password(CommonUtils.generateDefaultPassword(15))
                    .isVerified(true)
                    .isActive(true)
                    .build();
            userMetaData = userMetaDataRepository.save(userMetaData);
            UserRoleService userRoleService = UserRoleService.builder()
                    .user(newUser)
                    .service(services)
                    .role(roles)
                    .build();
            userRoleService = userRoleServiceRepository.save(userRoleService);
            return ResponseUtils.successResponse(AppConstant.SUCCESS_REGISTRATION_COMPLETED,null);
        }catch (Exception e){
            return ResponseUtils.internalServerError(AppConstant.INTERNAL_SERVER_ERROR_MESSAGE,null);
        }
    }

    public Roles getRoleById(Long id){
        System.out.println(id);
        Optional<Roles> rolesOptional = rolesRepository.findById(id);
        return rolesOptional.orElse(null);
    }

    public Services getServiceById(Long id){
        Optional<Services> serviceOptional = serviceRepository.findById(id);
        return serviceOptional.orElse(null);
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

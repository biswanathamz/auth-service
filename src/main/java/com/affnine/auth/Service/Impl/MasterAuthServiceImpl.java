package com.affnine.auth.Service.Impl;

import com.affnine.auth.Constant.AppConstant;
import com.affnine.auth.Model.RequestDto.Master.CreateRoleRequestDto;
import com.affnine.auth.Model.RequestDto.Master.UpdateRoleRequestDto;
import com.affnine.auth.Model.Roles;
import com.affnine.auth.Repository.RolesRepository;
import com.affnine.auth.Service.MasterAuthService;
import com.affnine.auth.Util.ResponseUtils;
import com.affnine.auth.Util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MasterAuthServiceImpl implements MasterAuthService {
    @Autowired
    RolesRepository rolesRepository;

    @Override
    public ResponseEntity<ServiceResponse<List<Roles>>> getRoles() {
        try{
            List<Roles> rolesList = rolesRepository.findAll();
            return ResponseUtils.successResponse(AppConstant.SUCCESS_GET_ALL_ROLES,rolesList);
        }catch (Exception e){
            return ResponseUtils.internalServerError(AppConstant.INTERNAL_SERVER_ERROR_MESSAGE,null);
        }
    }

    @Override
    public ResponseEntity<ServiceResponse<String>> createRole(CreateRoleRequestDto request) {
       try{
           if (rolesRepository.existsByName(request.getRoleName())) {
               return ResponseUtils.badRequest(AppConstant.ERROR_ROLE_NAME_ALREADY_EXISTS, null);
           }
           Roles role = new Roles();
           role.setName(request.getRoleName());
           rolesRepository.save(role);
           return ResponseUtils.successResponse(AppConstant.SUCCESS_ROLE_CREATED,null);
       }catch (Exception e){
           return ResponseUtils.internalServerError(AppConstant.INTERNAL_SERVER_ERROR_MESSAGE,null);
       }
    }

    @Override
    public ResponseEntity<ServiceResponse<String>> updateRole(Long roleId, UpdateRoleRequestDto request) {
        try{
            Optional<Roles> existingRole = rolesRepository.findById(roleId);

            if (existingRole.isEmpty()) {
                return ResponseUtils.badRequest(AppConstant.ERROR_ROLE_NOT_FOUND, null);
            }

            Roles role = existingRole.get();
            role.setName(request.getNewRoleName());
            rolesRepository.save(role);

            return ResponseUtils.successResponse(AppConstant.SUCCESS_ROLE_UPDATED,null);
        } catch (Exception e) {
            return ResponseUtils.internalServerError(AppConstant.INTERNAL_SERVER_ERROR_MESSAGE,null);
        }
    }

    @Override
    public ResponseEntity<ServiceResponse<String>> deleteRole(Long roleId) {
       try{
           if (!rolesRepository.existsById(roleId)) {
               return ResponseUtils.badRequest(AppConstant.ERROR_ROLE_NOT_FOUND, null);
           }
           rolesRepository.deleteById(roleId);
           return ResponseUtils.successResponse(AppConstant.SUCCESS_ROLE_DELETED,null);
       }catch (Exception e) {
           return ResponseUtils.internalServerError(AppConstant.INTERNAL_SERVER_ERROR_MESSAGE,null);
       }
    }

}

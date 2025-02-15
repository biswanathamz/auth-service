package com.affnine.auth.Controller;

import com.affnine.auth.Controller.Route.MasterAuthManage;
import com.affnine.auth.Model.RequestDto.Master.CreateRoleRequestDto;
import com.affnine.auth.Model.RequestDto.Master.UpdateRoleRequestDto;
import com.affnine.auth.Model.Roles;
import com.affnine.auth.Service.MasterAuthService;
import com.affnine.auth.Util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MasterAuthManageServiceController implements MasterAuthManage {

    @Autowired
    MasterAuthService masterAuthService;

    @Override
    public ResponseEntity<ServiceResponse<String>> createRole(CreateRoleRequestDto request) {
        return masterAuthService.createRole(request);
    }

    @Override
    public ResponseEntity<ServiceResponse<List<Roles>>> getRoles() {
        return masterAuthService.getRoles();
    }

    @Override
    public ResponseEntity<ServiceResponse<String>> updateRole(Integer id, UpdateRoleRequestDto request) {
        return masterAuthService.updateRole(Long.valueOf(id), request);
    }

    @Override
    public ResponseEntity<ServiceResponse<String>> deleteRole(Integer id) {
        return masterAuthService.deleteRole(Long.valueOf(id));
    }
}

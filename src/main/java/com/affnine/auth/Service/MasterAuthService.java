package com.affnine.auth.Service;

import com.affnine.auth.Model.RequestDto.Master.CreateRoleRequestDto;
import com.affnine.auth.Model.RequestDto.Master.UpdateRoleRequestDto;
import com.affnine.auth.Model.Roles;
import com.affnine.auth.Util.ServiceResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MasterAuthService {
    public ResponseEntity<ServiceResponse<List<Roles>>> getRoles();
    public ResponseEntity<ServiceResponse<String>> createRole(CreateRoleRequestDto request);
    public ResponseEntity<ServiceResponse<String>> updateRole(Long roleId, UpdateRoleRequestDto request);
    public ResponseEntity<ServiceResponse<String>> deleteRole(Long roleId);
}

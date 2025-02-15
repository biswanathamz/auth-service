package com.affnine.auth.Controller.Route;

import com.affnine.auth.Model.RequestDto.Master.CreateRoleRequestDto;
import com.affnine.auth.Model.RequestDto.Master.UpdateRoleRequestDto;
import com.affnine.auth.Model.Roles;
import com.affnine.auth.Util.ServiceResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "api/v1/auth/master")
public interface MasterAuthManage {
    @PostMapping(value = "/role/create")
    public ResponseEntity<ServiceResponse<String>> createRole(@Valid @RequestBody CreateRoleRequestDto request);
    @GetMapping(value = "/role/getAll")
    public ResponseEntity<ServiceResponse<List<Roles>>> getRoles();
    @PatchMapping(value = "/role/update/{id}")
    public ResponseEntity<ServiceResponse<String>> updateRole(@PathVariable Integer id,@RequestBody UpdateRoleRequestDto request);
    @DeleteMapping(value = "/role/delete/{id}")
    public ResponseEntity<ServiceResponse<String>> deleteRole(@PathVariable Integer id);
}

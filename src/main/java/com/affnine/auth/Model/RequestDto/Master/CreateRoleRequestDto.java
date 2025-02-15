package com.affnine.auth.Model.RequestDto.Master;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRoleRequestDto {
    @NotEmpty(message = "roleName can't be null")
    private String roleName;
}

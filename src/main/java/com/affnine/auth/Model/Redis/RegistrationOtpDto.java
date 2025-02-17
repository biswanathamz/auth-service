package com.affnine.auth.Model.Redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationOtpDto {
    private Integer otp;
    private String email;
    private Long applicationSourceId;
}

package com.affnine.auth.Model.RequestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterVerifyOtpRequestDto {
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "ApplicationSourceId can't be null")
    private Long applicationSourceId;

    @NotNull(message = "Otp can't be null")
    private Long otp;
}

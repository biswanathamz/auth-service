package com.affnine.auth.Model.RequestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterSendOtpRequestDto {
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "ApplicationSourceId can't be null")
    private Long applicationSourceId;
}

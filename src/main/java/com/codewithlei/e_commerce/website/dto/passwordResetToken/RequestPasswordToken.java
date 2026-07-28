package com.codewithlei.e_commerce.website.dto.passwordResetToken;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestPasswordToken {
    private String email;
}

package com.codewithlei.e_commerce.website.dto.passwordResetToken;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerifyCodeRequest {
    private String email;
    private int code;
}

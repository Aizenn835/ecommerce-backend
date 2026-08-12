package com.codewithlei.e_commerce.website.dto.user.updatePasswordUser;

import lombok.Data;

@Data
public class RequestNewPasswordDTO {
    private String oldPassword;
    private String newPassword;
}

package com.codewithlei.e_commerce.website.exception;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private int status;
    private String response;
    private LocalDateTime localDateTime;
}

package com.codewithlei.e_commerce.website.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private int status;
    private String response;
    @JsonFormat(pattern = "2026-09-04 18:11")
    private LocalDateTime localDateTime;
}

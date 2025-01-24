package com.fawry.moviesplatform.DTO;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponseDTO {
    private final String apiPath;
    private final HttpStatus httpStatus;
    private final String errorMessage;
    private final LocalDateTime timestamp;
}

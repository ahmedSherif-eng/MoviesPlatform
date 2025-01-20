package com.fawry.moviesplatform.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class ErrorResponseDTO {
    private  String apiPath;
    private  HttpStatus httpStatus;
    private  String errorMessage;
    private  LocalDateTime timestamp;
}

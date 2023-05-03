package com.medicalservice.medicalservice.configurations;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class CustomResponseError {
    private HttpStatus httpStatus;
    private String errorMessage;
}

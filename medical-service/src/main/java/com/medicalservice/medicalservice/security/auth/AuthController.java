package com.medicalservice.medicalservice.security.auth;

import com.medicalservice.medicalservice.configurations.CustomResponseError;
import com.medicalservice.medicalservice.security.dto.JWTRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/auth/token")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JWTUtility jwtUtility;
    @Autowired
    private AuthenticationManager authManager;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> authenticate(@RequestBody JWTRequest jwtRequest) throws Exception {
        try {
            return new ResponseEntity<>(this.authService.authenticate(jwtRequest, jwtUtility, authManager), HttpStatus.OK);
        }
        catch (Exception exc) {
            CustomResponseError error = new CustomResponseError(HttpStatus.UNAUTHORIZED, exc.getMessage());
            return new ResponseEntity<>(error, error.getHttpStatus());
        }
    }
}

package com.medicalservice.medicalservice.modules.AppUser.controller;

import com.medicalservice.medicalservice.configurations.CustomResponseError;
import com.medicalservice.medicalservice.modules.AppUser.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/user")
public class AppUserController {
    @Autowired
    private IAppUserService appUserService;

    @GetMapping
    public ResponseEntity getAll() {
        return new ResponseEntity<> (this.appUserService.getAll()
                .stream()
                .map(user -> this.appUserService.convertToDTO(user))
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity getById(@PathVariable String userId) {
        try {
            return new ResponseEntity<>(this.appUserService.getUserById(userId), HttpStatus.OK);
        }
        catch (Exception exc) {
            CustomResponseError error = new CustomResponseError(HttpStatus.NOT_FOUND, exc.getLocalizedMessage());
            return new ResponseEntity<>(error, error.getHttpStatus());
        }
    }
}
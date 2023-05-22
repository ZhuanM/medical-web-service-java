package com.medicalservice.medicalservice.modules.Doctor.controller;

import com.medicalservice.medicalservice.configurations.CustomResponseError;
import com.medicalservice.medicalservice.modules.Doctor.model.DoctorRegisterDTO;
import com.medicalservice.medicalservice.modules.Doctor.model.DoctorUpdateDTO;
import com.medicalservice.medicalservice.modules.Doctor.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4202")
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private IDoctorService doctorService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody DoctorRegisterDTO doctorRegisterDTO) {
        try {
            return new ResponseEntity<>(this.doctorService.save(doctorRegisterDTO), HttpStatus.CREATED);
        }
        catch (Exception exc) {
            CustomResponseError error = new CustomResponseError(HttpStatus.INTERNAL_SERVER_ERROR, exc.getLocalizedMessage());
            return new ResponseEntity<>(error, error.getHttpStatus());
        }
    }

    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @GetMapping
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<> (this.doctorService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @GetMapping(path = "/{doctorId}")
    public ResponseEntity<Object> getById(@PathVariable String doctorId) {
        try {
            return new ResponseEntity<>(this.doctorService.getById(doctorId), HttpStatus.OK);
        }
        catch (Exception exc) {
            CustomResponseError error = new CustomResponseError(HttpStatus.INTERNAL_SERVER_ERROR, exc.getLocalizedMessage());
            return new ResponseEntity<>(error, error.getHttpStatus());
        }
    }

    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @PatchMapping(path = "/{doctorId}")
    public ResponseEntity<Object> update(@PathVariable String doctorId, @RequestBody DoctorUpdateDTO payload) {
        try {
            return new ResponseEntity<>(this.doctorService.update(doctorId, payload), HttpStatus.OK);
        }
        catch (Exception exc) {
            CustomResponseError error = new CustomResponseError(HttpStatus.NOT_FOUND, exc.getMessage());
            return new ResponseEntity<>(error, error.getHttpStatus());
        }
    }

    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @DeleteMapping(path = "/{doctorId}")
    public ResponseEntity<Object> delete(@PathVariable String doctorId) {
        try {
            this.doctorService.delete(doctorId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception exc) {
            CustomResponseError error = new CustomResponseError(HttpStatus.NOT_FOUND, exc.getLocalizedMessage());
            return new ResponseEntity<>(error, error.getHttpStatus());
        }
    }

    @PreAuthorize("permitAll()")
    @GetMapping(path = "/specialities")
    public ResponseEntity<Object> listSpecialities() {
        try {
            return new ResponseEntity<>(this.doctorService.listSpecialities(), HttpStatus.OK);
        }
        catch (Exception exc) {
            CustomResponseError error = new CustomResponseError(HttpStatus.NOT_FOUND, exc.getLocalizedMessage());
            return new ResponseEntity<>(error, error.getHttpStatus());
        }
    }
}

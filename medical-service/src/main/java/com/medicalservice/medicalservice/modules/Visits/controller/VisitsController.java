package com.medicalservice.medicalservice.modules.Visits.controller;

import com.medicalservice.medicalservice.configurations.CustomResponseError;
import com.medicalservice.medicalservice.modules.Visits.models.VisitsCreateDTO;
import com.medicalservice.medicalservice.modules.Visits.models.VisitsUpdateDTO;
import com.medicalservice.medicalservice.modules.Visits.service.IVisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4202")
@RestController
@RequestMapping("/visits")
public class VisitsController {
    @Autowired
    private IVisitsService visitsService;

    @PreAuthorize("hasRole('ROLE_DOCTOR)")
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody VisitsCreateDTO payload) {
        try {
            return new ResponseEntity<>(this.visitsService.save(payload), HttpStatus.CREATED);
        } catch (Exception ex) {
            CustomResponseError error = new CustomResponseError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
            return new ResponseEntity<>(error, error.getHttpStatus());
        }
    }

    @PostAuthorize("hasRole('ROLE_DOCTOR') || hasRole('ROLE_PATIENT')")
    @GetMapping
    public ResponseEntity<Object> getAll(HttpServletRequest request) {
        return new ResponseEntity<>(this.visitsService.getAll(this.visitsService.getFilters(request)), HttpStatus.OK);
    }

    @GetMapping(path = "/{visitId}")
    public ResponseEntity<Object> getById(@PathVariable String visitId) {
        try {
            return new ResponseEntity<>(this.visitsService.getById(visitId), HttpStatus.OK);
        } catch (Exception ex) {
            CustomResponseError error = new CustomResponseError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
            return new ResponseEntity<>(error, error.getHttpStatus());
        }
    }

    @PostAuthorize("hasRole('ROLE_DOCTOR')")
    @PatchMapping(path = "/{visitId}")
    public ResponseEntity<Object> update(@PathVariable String visitId, @RequestBody VisitsUpdateDTO payload) {
        try {
            return new ResponseEntity<>(this.visitsService.update(visitId, payload), HttpStatus.OK);
        } catch (Exception ex) {
            CustomResponseError error = new CustomResponseError(HttpStatus.NOT_FOUND, ex.getMessage());
            return new ResponseEntity<>(error, error.getHttpStatus());
        }
    }

    @DeleteMapping(path = "/{visitId}")
    public ResponseEntity<Object> delete(@PathVariable String visitId) {
        try {
            this.visitsService.delete(visitId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            CustomResponseError error = new CustomResponseError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
            return new ResponseEntity<>(error, error.getHttpStatus());
        }
    }
}

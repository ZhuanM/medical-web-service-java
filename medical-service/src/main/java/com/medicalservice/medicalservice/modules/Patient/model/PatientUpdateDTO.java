package com.medicalservice.medicalservice.modules.Patient.model;

import lombok.Data;

import java.util.Date;

@Data
public class PatientUpdateDTO {
    private String name;
    private EngagedEntity gp;
    private Date healthTaxesPaidUntil;
}
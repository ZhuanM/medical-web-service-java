package com.medicalservice.medicalservice.modules.Visits.models;

import com.medicalservice.medicalservice.modules.Patient.model.EngagedEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class VisitsCreateDTO {
    private EngagedEntity patient;

    private EngagedEntity doctor;

    private String diagnosis;

    private String date;

    private List<Treatment> medicaments;

    private SickLeave sickLeave;

    public VisitsCreateDTO(EngagedEntity patient, EngagedEntity doctor, String diagnosis, List<Treatment> medicaments, SickLeave sickLeave) {
        this.patient = patient;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
        this.medicaments = medicaments;
        this.sickLeave = sickLeave;
    }

    public VisitsCreateDTO(EngagedEntity patient, EngagedEntity doctor, String diagnosis) {
        this.patient = patient;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
        this.medicaments = new ArrayList<>();
        this.sickLeave = null;
    }

    public VisitsCreateDTO(String date, EngagedEntity patient, EngagedEntity doctor) {
        this.patient = patient;
        this.doctor = doctor;
        this.diagnosis = "";
        this.date = date;
        this.medicaments = new ArrayList<>();
        this.sickLeave = null;
    }

    public VisitsCreateDTO(EngagedEntity patient, EngagedEntity doctor) {
        this.patient = patient;
        this.doctor = doctor;
        this.diagnosis = "";
        this.date = null;
        this.medicaments = new ArrayList<>();
        this.sickLeave = null;
    }
}

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
    private List<Treatment> treatments;
    private SickLeave sickLeave;

    public VisitsCreateDTO(EngagedEntity patient, EngagedEntity doctor, String diagnosis, List<Treatment> treatments, SickLeave sickLeave) {
        this.doctor = doctor;
        this.patient = patient;
        this.diagnosis = diagnosis;
        this.treatments = treatments;
        this.sickLeave = sickLeave;
    }

    public VisitsCreateDTO(EngagedEntity patient, EngagedEntity doctor, String diagnosis) {
        this.doctor = doctor;
        this.patient = patient;
        this.diagnosis = diagnosis;
        this.treatments = new ArrayList<>();
        this.sickLeave = null;
    }

    public VisitsCreateDTO(String date, EngagedEntity patient, EngagedEntity doctor) {
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
        this.diagnosis = "";
        this.treatments = new ArrayList<>();
        this.sickLeave = null;
    }

    public VisitsCreateDTO(EngagedEntity patient, EngagedEntity doctor) {
        this.date = null;
        this.doctor = doctor;
        this.patient = patient;
        this.diagnosis = "";
        this.treatments = new ArrayList<>();
        this.sickLeave = null;
    }
}

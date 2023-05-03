package com.medicalservice.medicalservice.modules.Visits.models;

import com.medicalservice.medicalservice.modules.Patient.model.EngagedEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mongodb.lang.NonNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "Visits")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Visits {
    @Id
    private String id;

    @NonNull
    private EngagedEntity patient;

    @NonNull
    private EngagedEntity doctor;

    @NonNull
    private String date;

    @NonNull
    private String diagnosis;

    private List<Treatment> medicaments;

    private SickLeave sickLeave;

    public Visits(
            @NonNull EngagedEntity patient,
            @NonNull EngagedEntity doctor,
            @NonNull String date,
            @NonNull String diagnosis,
            List<Treatment> medicaments,
            SickLeave sickLeave) {
        this.id = null;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.diagnosis = diagnosis;
        this.medicaments = medicaments;
        this.sickLeave = sickLeave;
    }

    public Visits(
            @NonNull EngagedEntity patient,
            @NonNull EngagedEntity doctor,
            @NonNull String date,
            @NonNull String diagnosis) {
        this.id = null;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.diagnosis = diagnosis;
        this.medicaments = new ArrayList<>();
        this.sickLeave = null;
    }

    public Visits(
            @NonNull EngagedEntity patient,
            @NonNull EngagedEntity doctor,
            @NonNull String date) {
        this.id = null;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.diagnosis = null;
        this.medicaments = new ArrayList<>();
        this.sickLeave = null;
    }
}

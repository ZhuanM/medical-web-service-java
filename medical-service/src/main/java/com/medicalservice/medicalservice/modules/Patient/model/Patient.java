package com.medicalservice.medicalservice.modules.Patient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mongodb.lang.NonNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "Patient")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Patient {
    @Id
    private String id;

    @Indexed(unique = true)
    private EngagedEntity engagedEntity;

    @NonNull
    private EngagedEntity gp;
    /*
    The unique civil number of the patient
     */
    @NonNull
    @Indexed(unique = true)
    private String UCN;

    @NonNull
    private String name;

    private Date healthTaxesPaidUntil;

    public Patient(
            @NonNull String UCN,
            @NonNull EngagedEntity engagedEntity,
            @NonNull EngagedEntity gp,
            @NonNull String name) {
        this.UCN = UCN;
        this.engagedEntity = engagedEntity;
        this.gp = gp;
        this.name = name;
        this.healthTaxesPaidUntil = null;
    }

    public Patient(
            @NonNull String UCN,
            @NonNull EngagedEntity engagedEntityd,
            @NonNull String name) {
        this.UCN = UCN;
        this.engagedEntity = engagedEntityd;
        this.gp = null;
        this.name = name;
        this.healthTaxesPaidUntil = null;
    }

    public Patient(
            @NonNull String UCN,
            @NonNull EngagedEntity engagedEntity,
            @NonNull String name,
            Date healthTaxesPaidUntil) {
        this.UCN = UCN;
        this.engagedEntity = engagedEntity;
        this.gp = null;
        this.name = name;
        this.healthTaxesPaidUntil = healthTaxesPaidUntil;
    }

    public Patient(
            @NonNull String UCN,
            @NonNull EngagedEntity engagedEntity,
            @NonNull EngagedEntity gp,
            @NonNull String name,
            Date healthTaxesPaidUntil) {
        this.UCN = UCN;
        this.engagedEntity = engagedEntity;
        this.gp = gp;
        this.name = name;
        this.healthTaxesPaidUntil = healthTaxesPaidUntil;
    }
}
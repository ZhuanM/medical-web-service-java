package com.medicalservice.medicalservice.modules.Patient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mongodb.lang.NonNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "Patient")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Patient {
    @Id
    private String id;

    @Indexed(unique = true, sparse = true)
    private EngagedEntity engagedEntity;

    @NonNull
    private EngagedEntity gp;

    @NonNull
    @Indexed(unique = true)
    private String UniqueCitizenNumber;

    @NonNull
    private String name;

    private Date healthTaxesPaidUntil;

    public Patient(@NonNull String UniqueCitizenNumber, @NonNull EngagedEntity engagedEntity, @NonNull EngagedEntity gp, @NonNull String name) {
        this.healthTaxesPaidUntil = null;
        this.name = name;
        this.UniqueCitizenNumber = UniqueCitizenNumber;
        this.gp = gp;
        this.engagedEntity = engagedEntity;
    }
}

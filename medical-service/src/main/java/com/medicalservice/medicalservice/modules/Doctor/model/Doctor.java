package com.medicalservice.medicalservice.modules.Doctor.model;

import com.medicalservice.medicalservice.modules.Patient.model.EngagedEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mongodb.lang.NonNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@NoArgsConstructor
@Document(collection = "Doctor")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Doctor {
    @Id
    private String id;

    @Indexed(unique = true)
    private String UniqueDoctorNumber;

    private EngagedEntity engagedEntity;

    @NonNull
    private String name;

    Set<Specialization> specializations;

    public Doctor(String UniqueDoctorNumber, EngagedEntity engagedEntity, @NonNull String name) {
        this.UniqueDoctorNumber = UniqueDoctorNumber;
        this.engagedEntity = engagedEntity;
        this.name = name;
    }

    public Doctor(String UniqueDoctorNumber, EngagedEntity engagedEntity, @NonNull String name, @NonNull Set<Specialization> specializations) {
        this.UniqueDoctorNumber = UniqueDoctorNumber;
        this.engagedEntity = engagedEntity;
        this.name = name;
        this.specializations = specializations;
    }
}

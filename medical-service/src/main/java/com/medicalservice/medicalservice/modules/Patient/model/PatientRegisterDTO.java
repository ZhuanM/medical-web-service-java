package com.medicalservice.medicalservice.modules.Patient.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientRegisterDTO {
    private String username;

    private String password;

    private String UCN;

    private EngagedEntity gp;

    private String name;

    public PatientRegisterDTO(String username, String password, String UCN, EngagedEntity gp, String name) {
        this.username = username;
        this.password = password;
        this.UCN = UCN;
        this.gp = gp;
        this.name = name;
    }

}

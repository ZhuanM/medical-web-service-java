package com.medicalservice.medicalservice.modules.Patient.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientRegisterDTO {
    private String username;

    private String password;

    private String UniqueCitizenNumber;

    private EngagedEntity gp;

    private String name;

    public PatientRegisterDTO(String username, String password, String UniqueCitizenNumber, EngagedEntity gp, String name) {
        this.username = username;
        this.password = password;
        this.UniqueCitizenNumber = UniqueCitizenNumber;
        this.gp = gp;
        this.name = name;
    }

}

package com.medicalservice.medicalservice.modules.Patient.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientRegisterDTO {
    private String name;
    private String username;
    private String password;
    private EngagedEntity gp;
    private String UniqueCitizenNumber;

    public PatientRegisterDTO(String username, String password, String UniqueCitizenNumber, EngagedEntity gp, String name) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.gp = gp;
        this.UniqueCitizenNumber = UniqueCitizenNumber;
    }
}

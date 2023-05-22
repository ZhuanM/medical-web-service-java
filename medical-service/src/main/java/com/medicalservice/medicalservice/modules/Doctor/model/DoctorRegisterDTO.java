package com.medicalservice.medicalservice.modules.Doctor.model;

import lombok.Data;

@Data
public class DoctorRegisterDTO {
    private String name;
    private String username;
    private String password;
    private Specialization specialization;
    private String UniqueDoctorNumber;

    public DoctorRegisterDTO(String username, String password, String UniqueDoctorNumber, String name, Specialization specialization) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.specialization = specialization;
        this.UniqueDoctorNumber = UniqueDoctorNumber;
    }
}

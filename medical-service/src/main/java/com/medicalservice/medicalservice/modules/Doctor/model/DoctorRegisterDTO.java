package com.medicalservice.medicalservice.modules.Doctor.model;

import lombok.Data;

@Data
public class DoctorRegisterDTO {
    private String username;

    private String password;

    private String UniqueDoctorNumber;

    private String name;

    private Specialization specialization;

    public DoctorRegisterDTO(String username, String password, String UniqueDoctorNumber, String name, Specialization specialization) {
        this.username = username;
        this.password = password;
        this.UniqueDoctorNumber = UniqueDoctorNumber;
        this.name = name;
        this.specialization = specialization;
    }
}

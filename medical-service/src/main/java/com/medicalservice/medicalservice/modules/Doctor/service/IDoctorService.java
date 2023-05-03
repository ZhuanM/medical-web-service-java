package com.medicalservice.medicalservice.modules.Doctor.service;

import com.medicalservice.medicalservice.modules.Doctor.model.Doctor;
import com.medicalservice.medicalservice.modules.Doctor.model.DoctorRegisterDTO;
import com.medicalservice.medicalservice.modules.Doctor.model.DoctorUpdateDTO;
import com.medicalservice.medicalservice.modules.Doctor.model.Specialization;

import java.util.List;

public interface IDoctorService {

    Doctor save(DoctorRegisterDTO doctorDTO) throws Exception;

    List<Doctor> getAll();

    Doctor getById(String doctorId) throws Exception;

    Doctor update(String doctorId, DoctorUpdateDTO payload) throws Exception;

    void delete(String doctorId) throws Exception;

    List<Specialization> listSpecialities();
}

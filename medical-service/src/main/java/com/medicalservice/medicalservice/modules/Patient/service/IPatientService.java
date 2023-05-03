package com.medicalservice.medicalservice.modules.Patient.service;

import com.medicalservice.medicalservice.modules.Patient.model.Patient;
import com.medicalservice.medicalservice.modules.Patient.model.PatientRegisterDTO;
import com.medicalservice.medicalservice.modules.Patient.model.PatientUpdateDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IPatientService {
    Patient save(PatientRegisterDTO patientRegisterDTO) throws Exception;
    List<Patient> getAll(Map<String, String> query);
    Patient getById(String patientId) throws Exception;
    Patient update(String id, PatientUpdateDTO payload) throws Exception;
    void delete(String id) throws Exception;

    Map<String, String> getFilters(HttpServletRequest request);
}

package com.medicalservice.medicalservice.modules.Patient.service;

import com.medicalservice.medicalservice.modules.AppUser.model.AppUser;
import com.medicalservice.medicalservice.modules.AppUser.service.IAppUserService;
import com.medicalservice.medicalservice.modules.Patient.model.EngagedEntity;
import com.medicalservice.medicalservice.modules.Patient.model.Patient;
import com.medicalservice.medicalservice.modules.Patient.model.PatientRegisterDTO;
import com.medicalservice.medicalservice.modules.Patient.model.PatientUpdateDTO;
import com.medicalservice.medicalservice.modules.Patient.repo.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientService implements IPatientService {
    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient save(PatientRegisterDTO patientRegisterDTO) throws Exception {
        if(this.UniqueCitizenNumberExists(patientRegisterDTO.getUniqueCitizenNumber())) throw new Exception("UniqueCitizenNumber exists!");

        AppUser user = new AppUser(
                patientRegisterDTO.getUsername(),
                patientRegisterDTO.getPassword(),
                "PATIENT"
        );

        user = this.appUserService.save(user);

        EngagedEntity gp = new EngagedEntity(
                patientRegisterDTO.getGp().getUserId(),
                patientRegisterDTO.getGp().getName()
        );

        EngagedEntity ee = new EngagedEntity(
                user.getId(),
                patientRegisterDTO.getName()
        );

        Patient patient = new Patient(
                patientRegisterDTO.getUniqueCitizenNumber(),
                ee,
                gp,
                patientRegisterDTO.getName()
        );
        return this.patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAll(Map<String, String> query) {
        List<String> combinedEntries = query.entrySet()
                .stream()
                .map(x ->
                        "{" + x.getKey() + ":" + "{$eq:" + x.getValue() + "}}")

                .collect(Collectors.toList());
        return this.patientRepository.findByQuery(query);
    }

    @Override
    public Patient getById(String patientId) throws Exception {
        Optional<Patient> patient = this.patientRepository.findById(patientId);
        if (patient.isEmpty()) throw new Exception("Patient not found!");
        return patient.get();
    }

    public Patient getByUserId(String userId) throws Exception {
        Optional<Patient> patient = this.patientRepository.findByUserId(userId);
        if (patient.isEmpty()) throw new Exception("Patient not found!");
        return patient.get();
    }

    @Override
    public Patient update(String id, PatientUpdateDTO payload) throws Exception {
        Patient patient = this.patientRepository.findById(id).orElse(null);

        if(patient == null) throw new Exception("Patient not found!");

        if(payload.getName() != null) {
            patient.setName(payload.getName());
        }
        if(payload.getGp() != null) {
            patient.setGp(payload.getGp());
        }
        if(payload.getHealthTaxesPaidUntil() != null) {
            patient.setHealthTaxesPaidUntil(payload.getHealthTaxesPaidUntil());
        }

        return this.patientRepository.save(patient);
    }

    @Override
    public void delete(String id) throws Exception {
        Patient patient = this.patientRepository.findById(id).orElse(null);

        if(patient == null) throw new Exception("Patient not found!");

        this.patientRepository.deleteById(id);
        this.appUserService.delete(patient.getEngagedEntity().getUserId());
    }

    private boolean UniqueCitizenNumberExists(String UniqueCitizenNumber) {
        return this.patientRepository.findByUniqueCitizenNumber(UniqueCitizenNumber).isPresent();
    }

    @Override
    public Map<String, String> getFilters(HttpServletRequest request) {
        Map<String, String> query = new HashMap<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.forEach((key, valArray) -> {
            for (String value : valArray) {
                query.put(key, value);
            }
        });

        return query;
    }
}

package com.medicalservice.medicalservice.modules.Visits.service;

import com.medicalservice.medicalservice.modules.Doctor.service.DoctorService;
import com.medicalservice.medicalservice.modules.Patient.service.PatientService;
import com.medicalservice.medicalservice.modules.Visits.models.Visits;
import com.medicalservice.medicalservice.modules.Visits.models.VisitsCreateDTO;
import com.medicalservice.medicalservice.modules.Visits.models.VisitsUpdateDTO;
import com.medicalservice.medicalservice.modules.Visits.repo.VisitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class VisitsService implements IVisitsService {
    @Autowired
    private VisitsRepository visitsRepository;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Override
    public Visits save(VisitsCreateDTO visitDTO) throws Exception {
        Visits visit;
        if (visitDTO.getDiagnosis() == null) {
            visit = new Visits(
                    visitDTO.getPatient(),
                    visitDTO.getDoctor(),
                    visitDTO.getDate()
            );
        }
        else {
            visit = new Visits(
                    visitDTO.getPatient(),
                    visitDTO.getDoctor(),
                    LocalDate.now().toString(),
                    visitDTO.getDiagnosis()
            );
        }

        if (visitDTO.getTreatments() != null) {
            visit.setTreatments(visitDTO.getTreatments());
        }

        if (visitDTO.getSickLeave() != null) {
            visit.setSickLeave(visitDTO.getSickLeave());
        }

        return this.visitsRepository.save(visit);
    }

    @Override
    public List<Visits> getAll(Map<String, String> query) {
        List<String> combinedEntries = query.entrySet()
                .stream()
                .map(x -> Objects.equals(x.getKey(), "date") ?
                        "{" + x.getKey() + ":" + "{$eq: " + x.getValue() + "}}" :
                        "{" + x.getKey() + ":" + "{$eq:" + x.getValue() + "}}")
                .collect(Collectors.toList());

        return this.visitsRepository.findByQuery(query);
    }

    @Override
    public Visits getById(String visitId) throws Exception {
        Optional<Visits> visit = this.visitsRepository.findById(visitId);
        if (visit.isEmpty()) {
            throw new Exception("Visit not found!");
        }

        return visit.get();
    }

    @Override
    public void delete(String visitId) throws Exception {
        Optional<Visits> visit = this.visitsRepository.findById(visitId);

        if (visit.isEmpty()) throw new Exception("Visit not found!");

        this.visitsRepository.deleteById(visitId);
    }

    @Override
    public Visits update(String visitId, VisitsUpdateDTO payload) throws Exception {
        Visits visit = this.visitsRepository.findById(visitId).orElse(null);

        if (visit == null) {
            throw new Exception("Visit not found!");
        }

        if (payload.getSickLeave() != null) {
            visit.setSickLeave(payload.getSickLeave());
        }

        if (payload.getTreatments() != null) {
            visit.setTreatments(payload.getTreatments());
        }

        if (!payload.getDiagnosis().isEmpty()) {
            visit.setDiagnosis(payload.getDiagnosis());
        }

        return this.visitsRepository.save(visit);
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

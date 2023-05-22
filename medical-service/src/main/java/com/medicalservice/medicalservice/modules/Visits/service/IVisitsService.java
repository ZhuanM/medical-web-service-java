package com.medicalservice.medicalservice.modules.Visits.service;

import com.medicalservice.medicalservice.modules.Visits.models.Visits;
import com.medicalservice.medicalservice.modules.Visits.models.VisitsCreateDTO;
import com.medicalservice.medicalservice.modules.Visits.models.VisitsUpdateDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IVisitsService {
    Visits save(VisitsCreateDTO patientRegisterDTO) throws Exception;
    List<Visits> getAll(Map<String, String> query);
    Visits getById(String patientId) throws Exception;
    void delete(String id) throws Exception;
    Visits update(String id, VisitsUpdateDTO payload) throws Exception;
    Map<String, String> getFilters(HttpServletRequest request);
}

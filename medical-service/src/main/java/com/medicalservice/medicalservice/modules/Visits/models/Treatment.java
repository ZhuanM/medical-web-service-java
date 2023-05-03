package com.medicalservice.medicalservice.modules.Visits.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Treatment {
    private String medicamentName;
    private String pharmaceuticalCompany;
    private String dosage;
}

package com.medicalservice.medicalservice.modules.Visits.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class SickLeave {
    private Date startDate;
    private Date endDate;
    private Integer numberOfDays;
}

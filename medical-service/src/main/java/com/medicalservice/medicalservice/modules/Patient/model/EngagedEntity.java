package com.medicalservice.medicalservice.modules.Patient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EngagedEntity {
    private String userId;
    private String href;
    private String name;

    public EngagedEntity(String userId) {
        this.userId = userId;
        this.href = null;
    }

    public EngagedEntity(String userId, String name) {
        this.userId = userId;
        this.href = null;
        this.name = name;
    }
}

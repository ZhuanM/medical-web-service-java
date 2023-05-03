package com.medicalservice.medicalservice.modules.AppUser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDTO {
    private String id;
    private String username;
    private String role;
}

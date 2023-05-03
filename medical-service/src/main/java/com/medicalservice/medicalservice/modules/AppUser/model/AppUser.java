package com.medicalservice.medicalservice.modules.AppUser.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mongodb.lang.NonNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "AppUser")
@JsonInclude(JsonInclude.Include.NON_NULL)  //stores only not null values
public class AppUser {
    @Id
    private String id;
    @Indexed(unique=true)
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String role;

    public AppUser(@NonNull String username, @NonNull String password, @NonNull String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
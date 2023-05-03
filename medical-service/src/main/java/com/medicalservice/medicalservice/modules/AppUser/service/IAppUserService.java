package com.medicalservice.medicalservice.modules.AppUser.service;

import com.medicalservice.medicalservice.modules.AppUser.model.AppUser;
import com.medicalservice.medicalservice.modules.AppUser.model.AppUserDTO;
import com.medicalservice.medicalservice.modules.AppUser.model.AppUserUpdateDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IAppUserService extends UserDetailsService {
    AppUser save(AppUser appUser) throws Exception;

    List<AppUser> getAll();

    Optional<AppUser> getById(String id);

    AppUserDTO getUserById(String id) throws Exception;

    AppUserDTO getUserByUsername(String username) throws Exception;
    AppUser findUserByUsername(String username) throws Exception;

    AppUser update(String id, AppUserUpdateDTO payload) throws Exception;

    void delete(String id) throws Exception;

    boolean usernameExists(String username);

    boolean hashedPasswordMatches(String password, String password1);

    AppUserDTO convertToDTO(AppUser appUser);
}
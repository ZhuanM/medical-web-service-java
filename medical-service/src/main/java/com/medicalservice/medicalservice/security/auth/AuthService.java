package com.medicalservice.medicalservice.security.auth;

import com.medicalservice.medicalservice.modules.AppUser.model.AppUser;
import com.medicalservice.medicalservice.modules.AppUser.service.AppUserService;
import com.medicalservice.medicalservice.modules.Doctor.service.DoctorService;
import com.medicalservice.medicalservice.modules.Patient.service.PatientService;
import com.medicalservice.medicalservice.security.dto.JWTRequest;
import com.medicalservice.medicalservice.security.dto.JWTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Lazy
@Service
@Transactional
public class AuthService {
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;

    public JWTResponse authenticate(JWTRequest jwtRequest, JWTUtility jwtUtility, AuthenticationManager authenticationManager) throws Exception {
        AppUser user;

        try {
            user = appUserService.findUserByUsername(jwtRequest.getUsername());
        } catch (UsernameNotFoundException e) {
            throw new Exception("Invalid Credentials!", e);
        }

        try {
            if (this.appUserService.hashedPasswordMatches(jwtRequest.getPassword(), user.getPassword())) {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                jwtRequest.getUsername(),
                                jwtRequest.getPassword()
                        )
                );
            } else {
                throw new Exception("Invalid Credentials!");
            }
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials!", e);
        }

        final UserDetails userDetails = this.appUserService.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtUtility.generateToken(userDetails);

        String userId = this.getIDofUserByRole(user.getId(), user.getRole());
        if (userId.isEmpty()) userId = user.getId();

        return new JWTResponse(userId, user.getUsername(), user.getRole(), token);
    }

    private String getIDofUserByRole(String userId, String role) throws Exception {
        if (role.equals("PATIENT")) {
            return this.patientService.getByUserId(userId).getId();
        }
        if (role.equals("DOCTOR")) {
            return this.doctorService.getByUserId(userId).getId();
        }
        return "";
    }

    public UserDetails loadUser(String username) {
        return this.appUserService.loadUserByUsername(username);
    }
}

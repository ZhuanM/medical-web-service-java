package com.medicalservice.medicalservice.modules.AppUser.service;

import com.medicalservice.medicalservice.modules.AppUser.model.AppUser;
import com.medicalservice.medicalservice.modules.AppUser.model.AppUserDTO;
import com.medicalservice.medicalservice.modules.AppUser.model.AppUserUpdateDTO;
import com.medicalservice.medicalservice.modules.AppUser.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppUserService implements IAppUserService, UserDetailsService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUsername(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("Wrong credentials!");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public AppUser save(AppUser appUser) throws Exception {
        if (this.usernameExists(appUser.getUsername())) {
            throw new Exception("Username exists!");
        }

        appUser.setPassword(this.passwordEncoder.encode(appUser.getPassword()));
        return this.appUserRepository.save(appUser);
    }

    @Override
    public List<AppUser> getAll() {
        return this.appUserRepository.findAll();
    }

    @Override
    public Optional<AppUser> getById(String id) {
        return this.appUserRepository.findById(id);
    }

    @Override
    public AppUserDTO getUserById(String id) throws Exception {
        Optional<AppUser> optionalUser = this.getById(id);
        if (optionalUser.isEmpty()) {
            throw new Exception("User Not Found!");
        }

        return this.convertToDTO(optionalUser.get());
    }

    @Override
    public AppUser findUserByUsername(String username) throws Exception {
        Optional<AppUser> optionalUser = this.appUserRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new Exception("User Not Found!");
        }

        return optionalUser.get();
    }

    @Override
    public void delete(String id) throws Exception {
        AppUser user = this.appUserRepository.findById(id).orElse(null);

        if (user == null) {
            throw new Exception("User Not Found!");
        }

        this.appUserRepository.deleteById(id);
    }

    @Override
    public AppUser update(String id, AppUserUpdateDTO payload) throws Exception {
        AppUser user = this.appUserRepository.findById(id).orElse(null);

        if (user == null) {
            throw new Exception("User Not Found!");
        }

        if (!payload.getPassword().isEmpty()) {
            user.setPassword(payload.getPassword());
        }
        return this.appUserRepository.save(user);
    }

    public AppUserDTO convertToDTO(AppUser appUser) {
        return new AppUserDTO(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getRole()
        );
    }

    public boolean hashedPasswordMatches(String payloadPassword, String hashedPassword) {
        return this.passwordEncoder.matches(payloadPassword, hashedPassword);
    }

    public boolean usernameExists(String username) {
        return this.appUserRepository.findByUsername(username).isPresent();
    }
}

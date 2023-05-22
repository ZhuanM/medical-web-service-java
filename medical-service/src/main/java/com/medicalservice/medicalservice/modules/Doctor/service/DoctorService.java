package com.medicalservice.medicalservice.modules.Doctor.service;

import com.medicalservice.medicalservice.modules.AppUser.model.AppUser;
import com.medicalservice.medicalservice.modules.AppUser.service.IAppUserService;
import com.medicalservice.medicalservice.modules.Doctor.model.Doctor;
import com.medicalservice.medicalservice.modules.Doctor.model.DoctorRegisterDTO;
import com.medicalservice.medicalservice.modules.Doctor.model.DoctorUpdateDTO;
import com.medicalservice.medicalservice.modules.Doctor.model.Specialization;
import com.medicalservice.medicalservice.modules.Doctor.repo.DoctorRepository;
import com.medicalservice.medicalservice.modules.Patient.model.EngagedEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class DoctorService implements IDoctorService {
    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor save(DoctorRegisterDTO doctorDTO) throws Exception {
        if(this.UniqueDoctorNumberExists(doctorDTO.getUniqueDoctorNumber())) {
            throw new Exception("Doctor with this UniqueDoctorNumber already exists!");
        }

        AppUser user = new AppUser(
                doctorDTO.getUsername(),
                doctorDTO.getPassword(),
                "DOCTOR"
        );
        user = this.appUserService.save(user);

        EngagedEntity en = new EngagedEntity(
                user.getId(),
                doctorDTO.getName()
        );

        Set<Specialization> specializations = new LinkedHashSet<>();
        specializations.add(doctorDTO.getSpecialization());

        Doctor doctor = new Doctor(
                doctorDTO.getUniqueDoctorNumber(),
                en,
                doctorDTO.getName(),
                specializations
        );
        return this.doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAll() {
        return this.doctorRepository.findAll();
    }

    @Override
    public Doctor getById(String doctorId) throws Exception {
        Optional<Doctor> doctor = this.doctorRepository.findById(doctorId);
        if (doctor.isEmpty()) {
            throw new Exception("Doctor not found!");
        }

        return doctor.get();
    }

    public Doctor getByUserId(String userId) throws Exception {
        Optional<Doctor> doctor = this.doctorRepository.findByUserId(userId);
        if (doctor.isEmpty()) {
            throw new Exception("Doctor not found!");
        }

        return doctor.get();
    }

    @Override
    public void delete(String doctorId) throws Exception {
        Doctor doctor = this.doctorRepository.findById(doctorId).orElse(null);

        if(doctor == null) {
            throw new Exception("Patient not found!");
        }

        this.doctorRepository.deleteById(doctorId);
        this.appUserService.delete(doctor.getEngagedEntity().getUserId());
    }

    @Override
    public Doctor update(String doctorId, DoctorUpdateDTO payload) throws Exception {
        Doctor doctor = this.doctorRepository.findById(doctorId).orElse(null);

        if(doctor == null) {
            throw new Exception("Doctor not found!");
        }

        if(payload.getName() != null) {
            doctor.setName(payload.getName());
        }

        if(payload.getSpecializations() != null) {
            for (Specialization specialization : payload.getSpecializations()) {
                try {
                    Specialization.valueOf(specialization.toString());
                } catch (Exception e) {
                    throw new Exception("One of the specializations is incorrect");
                }
            }
            doctor.setSpecializations(payload.getSpecializations());
        }

        return this.doctorRepository.save(doctor);
    }

    @Override
    public List<Specialization> listSpecializations() {
        return new ArrayList<>(EnumSet.allOf(Specialization.class));
    }

    private boolean UniqueDoctorNumberExists(String UniqueDoctorNumber) {
        return this.doctorRepository.findByUniqueDoctorNumber(UniqueDoctorNumber).isPresent();
    }
}

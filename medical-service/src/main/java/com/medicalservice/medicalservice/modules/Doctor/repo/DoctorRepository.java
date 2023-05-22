package com.medicalservice.medicalservice.modules.Doctor.repo;

import com.medicalservice.medicalservice.modules.Doctor.model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor, String> {
    @Query("{ 'id' : ?0 }")
    Optional<Doctor> findById(String s);

    @Query("{ 'engagedEntity.userId': ?0 } }")
    Optional<Doctor> findByUserId(String userId);

    @Query("{ 'UniqueDoctorNumber' : ?0 }")
    Optional<Doctor> findByUniqueDoctorNumber(String UniqueDoctorNumber);
}

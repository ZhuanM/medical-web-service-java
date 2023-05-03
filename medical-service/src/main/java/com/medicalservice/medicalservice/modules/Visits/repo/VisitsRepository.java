package com.medicalservice.medicalservice.modules.Visits.repo;

import com.medicalservice.medicalservice.modules.Visits.models.Visits;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VisitsRepository extends MongoRepository<Visits, String> {
    @Query("{'$and': [?0]}")
    List<Visits> findByQuery( Map<String, String> query);
}

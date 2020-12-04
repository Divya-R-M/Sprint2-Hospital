package com.cg.healthassist.hospitalmodule.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.healthassist.hospitalmodule.domain.PatientTreatmentHistory;

@Repository
public interface PatientTreatmentHistoryRepository extends CrudRepository<PatientTreatmentHistory, Long> {

	
}

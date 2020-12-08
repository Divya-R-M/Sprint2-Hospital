package com.cg.healthassist.hospitalmodule.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.healthassist.hospitalmodule.domain.PatientTreatmentHistory;
/*
 * creating PatientTreatmentHistoryRespository Interface which extends JpaRespository 
 * which provides CRUD operations
 *
 */
@Repository
public interface PatientTreatmentHistoryRepository extends CrudRepository<PatientTreatmentHistory, Long> {

	PatientTreatmentHistory findByPatientId(String patientId);

	
}

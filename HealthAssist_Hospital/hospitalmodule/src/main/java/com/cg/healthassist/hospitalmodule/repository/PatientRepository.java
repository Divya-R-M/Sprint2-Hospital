package com.cg.healthassist.hospitalmodule.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.healthassist.hospitalmodule.domain.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

	
}

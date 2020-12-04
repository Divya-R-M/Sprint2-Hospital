package com.cg.healthassist.hospitalmodule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.healthassist.hospitalmodule.domain.PatientTreatmentHistory;
import com.cg.healthassist.hospitalmodule.exception.PatientIDException;
import com.cg.healthassist.hospitalmodule.repository.PatientTreatmentHistoryRepository;

@Service
public class PatientTreatmentHistoryService {

	@Autowired
	private PatientTreatmentHistoryRepository patientTreatmentHistoryRepository;

	public PatientTreatmentHistory saveOrUpdate(PatientTreatmentHistory patientTreatmentHistory) {

		try {
			patientTreatmentHistory.setPatientId(patientTreatmentHistory.getPatientId().toUpperCase());
			return patientTreatmentHistoryRepository.save(patientTreatmentHistory);
		} catch (Exception e) {
			throw new PatientIDException("PatientId " + patientTreatmentHistory.getPatientId() + " is already available");
		}
	}

}

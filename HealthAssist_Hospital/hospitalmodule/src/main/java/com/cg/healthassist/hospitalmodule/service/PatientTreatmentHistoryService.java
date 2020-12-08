package com.cg.healthassist.hospitalmodule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.healthassist.hospitalmodule.domain.PatientTreatmentHistory;
import com.cg.healthassist.hospitalmodule.exception.PatientIDException;
import com.cg.healthassist.hospitalmodule.repository.PatientTreatmentHistoryRepository;
/*
* Creating PatientTreatmentHistoryService class which provides different services for PatientTreatmentHistoryService class
*/

@Service
public class PatientTreatmentHistoryService {

	/*
	 * Declaring patientTreatmentHistoryRepository instance and autowired to
	 * instantiate the object
	 */
	@Autowired
	private PatientTreatmentHistoryRepository patientTreatmentHistoryRepository;

	public PatientTreatmentHistory savePatientTreatmentHistory(PatientTreatmentHistory patient) {

		try {
			return patientTreatmentHistoryRepository.save(patient);
		} catch (Exception e) {
			throw new PatientIDException("PatientId " + patient.getPatientId() + "is already available");
		}
	}

	public PatientTreatmentHistory updateByDiagnosis(String patientId, String diagnosis) {
		PatientTreatmentHistory patientTreatmentHistory = patientTreatmentHistoryRepository.findByPatientId(patientId);
		if (patientTreatmentHistory == null) {
			throw new PatientIDException("Treatment history with " + patientId + " does not exist to update");

		}
		patientTreatmentHistory.setDiagnosis(diagnosis);
		return patientTreatmentHistoryRepository.save(patientTreatmentHistory);
	}

	public PatientTreatmentHistory updateByTreatmentDate(String patientId, String treatmentDate) {
		PatientTreatmentHistory patientTreatmentHistory = patientTreatmentHistoryRepository.findByPatientId(patientId);
		if (patientTreatmentHistory == null) {
			throw new PatientIDException("Treatment history with " + patientId + " does not exist to update");

		}
		patientTreatmentHistory.setTreatmentDate(treatmentDate);
		return patientTreatmentHistoryRepository.save(patientTreatmentHistory);
	}
}
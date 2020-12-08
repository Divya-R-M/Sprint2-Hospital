package com.cg.healthassist.hospitalmodule.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.healthassist.hospitalmodule.domain.PatientTreatmentHistory;
import com.cg.healthassist.hospitalmodule.service.MapValidationErrorService;
import com.cg.healthassist.hospitalmodule.service.PatientTreatmentHistoryService;

/*
 * creating a class PatientTreatmentHistoryController which controls all the services related to PatientTreatmentHistory
 * and maps the services
 */
@RestController
@RequestMapping("/patientTreatmentHistory")
public class PatientTreatmentHistoryController {

	/*
	 * PatientTreatmentHistoryService and MapValidationErrorService objects are
	 * instantiated with the help of @Autowired
	 */
	@Autowired
	private PatientTreatmentHistoryService patientHistoryService;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@PostMapping("")
	public ResponseEntity<?> createNewPatient(@Valid @RequestBody PatientTreatmentHistory patient,
			BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		PatientTreatmentHistory newPatient = patientHistoryService.savePatientTreatmentHistory(patient);
		return new ResponseEntity<PatientTreatmentHistory>(newPatient, HttpStatus.CREATED);
	}

	@GetMapping("/byDiagnosis/{Id}/{diagnosis}")
	public ResponseEntity<?> updateByDiagnosis(@PathVariable("Id") String patientId, @PathVariable String diagnosis) {
		PatientTreatmentHistory patientTreatmentHistory = patientHistoryService.updateByDiagnosis(patientId, diagnosis);
		return new ResponseEntity<PatientTreatmentHistory>(patientTreatmentHistory, HttpStatus.CREATED);
	}

	@GetMapping("/byTreatmentDate/{Id}/{treatmentDate}")
	public ResponseEntity<?> updateByTreatmentDate(@PathVariable("Id") String patientId,
			@PathVariable String treatmentDate) {
		PatientTreatmentHistory patientTreatmentHistory = patientHistoryService.updateByTreatmentDate(patientId,
				treatmentDate);
		return new ResponseEntity<PatientTreatmentHistory>(patientTreatmentHistory, HttpStatus.CREATED);
	}
}

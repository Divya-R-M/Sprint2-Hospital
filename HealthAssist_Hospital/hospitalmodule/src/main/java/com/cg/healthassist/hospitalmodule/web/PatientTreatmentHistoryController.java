package com.cg.healthassist.hospitalmodule.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.healthassist.hospitalmodule.domain.PatientTreatmentHistory;
import com.cg.healthassist.hospitalmodule.service.MapValidationErrorService;
import com.cg.healthassist.hospitalmodule.service.PatientTreatmentHistoryService;

@RestController
@RequestMapping("/patientTreatmentHistory")
public class PatientTreatmentHistoryController {

	@Autowired
	private PatientTreatmentHistoryService patientTreatmentHistoryService;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@PostMapping("")
	public ResponseEntity<?> createNewPatient(@Valid @RequestBody PatientTreatmentHistory patientTreatmentHistory,BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		PatientTreatmentHistory newPatient = patientTreatmentHistoryService.saveOrUpdate(patientTreatmentHistory);
		return new ResponseEntity<PatientTreatmentHistory>(newPatient, HttpStatus.CREATED);
	}

}

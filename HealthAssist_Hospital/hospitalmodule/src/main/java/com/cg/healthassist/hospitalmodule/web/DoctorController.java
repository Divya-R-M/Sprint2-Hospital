package com.cg.healthassist.hospitalmodule.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.healthassist.hospitalmodule.domain.Doctor;
import com.cg.healthassist.hospitalmodule.service.DoctorService;
import com.cg.healthassist.hospitalmodule.service.MapValidationErrorService;

/*
 * creating a class DoctorController which controls all the services related to Doctor
 * and maps the services
 */
@RestController
@RequestMapping("/doctors")
public class DoctorController {

	/*
	 * DoctorService and MapValidationErrorService objects are instantiated with the
	 * help of @Autowired
	 */
	@Autowired
	private DoctorService doctorService;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@PostMapping("")
	public ResponseEntity<?> createNewDoctor(@Valid @RequestBody Doctor doctor, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		Doctor newDoctor = doctorService.saveDoctor(doctor);
		return new ResponseEntity<Doctor>(newDoctor, HttpStatus.CREATED);
	}

	@GetMapping("/ById/{doctorId}")
	public ResponseEntity<?> getDoctorById(@PathVariable String doctorId) {
		return new ResponseEntity<Doctor>(doctorService.findDoctorByDoctorId(doctorId), HttpStatus.OK);
	}

	@GetMapping("/all")
	public Iterable<Doctor> viewAllAvailableDoctors() {
		return doctorService.findAllDoctors();
	}

	@DeleteMapping("/delete/{doctorId}")
	public ResponseEntity<?> removeDoctor(@PathVariable("doctorId") String doctorId) {
		doctorService.delete(doctorId);
		return new ResponseEntity<String>("Doctor with Id : " + doctorId + " Deleted!", HttpStatus.OK);
	}

	@GetMapping("/updateDepartment/{id}/{department}")
	public ResponseEntity<?> updateByDepartment(@PathVariable("id") String doctorId,
			@PathVariable("department") String department) {
		Doctor doctor = doctorService.updateDepartment(doctorId, department);
		return new ResponseEntity<Doctor>(doctor, HttpStatus.CREATED);
	}

	@GetMapping("/updatePhoneNo/{id}/{doctorPhNo}")
	public ResponseEntity<?> updateByPhoneNumber(@PathVariable("id") String doctorId, @PathVariable Long doctorPhNo) {
		Doctor doctor = doctorService.updatePhoneNo(doctorId, doctorPhNo);
		return new ResponseEntity<Doctor>(doctor, HttpStatus.CREATED);
	}

	@GetMapping("/ByCategory/{specialization}")
	public List<Doctor> viewListBySpecialization(@PathVariable("specialization") String specialization) {
		return doctorService.findByDoctorSpecialization(specialization);
	}

}

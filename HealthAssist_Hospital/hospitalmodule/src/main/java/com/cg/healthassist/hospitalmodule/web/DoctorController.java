package com.cg.healthassist.hospitalmodule.web;

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

@RestController
@RequestMapping("/doctors")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewDoctor(@Valid @RequestBody Doctor doctor, BindingResult result) {
		
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) return errorMap;
		Doctor newDoctor=doctorService.saveOrUpdate(doctor);
		return new ResponseEntity<Doctor>(newDoctor,HttpStatus.CREATED);
}
	@GetMapping("/{doctorId}")
	public ResponseEntity<?> getDoctorById(@PathVariable String doctorId){
		return new ResponseEntity<Doctor>(doctorService.findDoctorByDoctorId(doctorId),HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<Doctor> getAllDoctors(){
		return doctorService.findAllDoctors();
	}
	
	@DeleteMapping("/{doctorId}")
	public ResponseEntity<?> deleteDoctor(@PathVariable String doctorId){
		doctorService.deleteDoctorByDoctorId(doctorId);
		return new ResponseEntity<String> ("Doctor with Id : "+ doctorId.toUpperCase() +" Deleted!",HttpStatus.OK);
	}
	
}

package com.cg.healthassist.hospitalmodule.web;

import java.util.List;

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

import com.cg.healthassist.hospitalmodule.domain.Appoinment;
import com.cg.healthassist.hospitalmodule.service.AppoinmentService;
import com.cg.healthassist.hospitalmodule.service.MapValidationErrorService;

/*
 * creating a class AppoinmentController which controls all the services related to Appoinment
 * and maps the services
 */
@RestController
@RequestMapping("/appoinments")
public class AppoinmentController {

	/*
	 * AppoinmentService and MapValidationErrorService objects are instantiated with
	 * the help of @Autowired
	 */
	@Autowired
	private AppoinmentService appoinmentService;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@PostMapping("/add")
	public ResponseEntity<?> createNewAppoinment(@Valid @RequestBody Appoinment appoinment, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		Appoinment newAppoinment = appoinmentService.saveOrUpdate(appoinment);
		return new ResponseEntity<Appoinment>(newAppoinment, HttpStatus.CREATED);
	}

	@GetMapping("/get/{doctorId}")
	public List<Appoinment> viewListByDoctorId(@PathVariable String doctorId) {
		return appoinmentService.findByDoctorId(doctorId);
	}
}

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

import com.cg.healthassist.hospitalmodule.domain.Bed;
import com.cg.healthassist.hospitalmodule.service.BedService;
import com.cg.healthassist.hospitalmodule.service.MapValidationErrorService;

/*
 * creating a class DoctorController which controls all the services related to Doctor
 * and maps the services
 */
@RestController
@RequestMapping("/beds")
public class BedController {

	/*
	 * BedService and MapValidationErrorService objects are instantiated with the
	 * help of @Autowired
	 */
	@Autowired
	private BedService bedService;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@PostMapping("")
	public ResponseEntity<?> createNewBed(@Valid @RequestBody Bed bed, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		Bed newBed = bedService.save(bed);
		return new ResponseEntity<Bed>(newBed, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public List<Bed> getAllBeds() {
		return bedService.findAllBeds();
	}

	@GetMapping("/get/{bedId}")
	public ResponseEntity<?> getBedById(@PathVariable String bedId) {
		return new ResponseEntity<Bed>(bedService.findBedByBedId(bedId), HttpStatus.OK);
	}

	@DeleteMapping("/remove/{bedId}")
	public ResponseEntity<?> removeBed(@PathVariable("bedId") String bedId) {
		bedService.remove(bedId);
		return new ResponseEntity<String>("Bed with Id : " + bedId + " Deleted!", HttpStatus.OK);
	}

	@GetMapping("/updateBedType/{id}/{bedType}")
	public ResponseEntity<?> updateBedType(@PathVariable("id") String doctorId,
			@PathVariable("bedType") String bedType) {
		Bed bed = bedService.updateBedType(doctorId, bedType);
		return new ResponseEntity<Bed>(bed, HttpStatus.CREATED);
	}
}

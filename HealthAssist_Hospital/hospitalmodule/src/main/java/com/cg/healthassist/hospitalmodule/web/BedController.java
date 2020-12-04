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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.healthassist.hospitalmodule.domain.Bed;
import com.cg.healthassist.hospitalmodule.service.BedService;
import com.cg.healthassist.hospitalmodule.service.MapValidationErrorService;

@RestController
@RequestMapping("/beds")
public class BedController {

	@Autowired
	private BedService bedService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewBed(@Valid @RequestBody Bed bed, BindingResult result) {
		
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) return errorMap;
		Bed newBed=bedService.saveOrUpdate(bed);
		return new ResponseEntity<Bed>(newBed,HttpStatus.CREATED);
}
	@GetMapping("/{bedId}")
	public ResponseEntity<?> getBedById(@PathVariable String bedId){
		return new ResponseEntity<Bed>(bedService.findBedByBedId(bedId),HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<Bed> getAllBeds(){
		return bedService.findAllBeds();
	}
	
	@DeleteMapping("/{bedId}")
	public ResponseEntity<?> deleteProject(@PathVariable String bedId){
		bedService.deleteBedByBedId(bedId);
		return new ResponseEntity<String> ("Bed with Id : "+ bedId.toUpperCase() +" Deleted!",HttpStatus.OK);
	}
	
	@PutMapping("")
	public ResponseEntity<?> updateBed(@Valid @RequestBody Bed bed, BindingResult result) {
		
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) return errorMap;
		Bed updateBed=bedService.update(bed);
		return new ResponseEntity<Bed>(updateBed,HttpStatus.CREATED);
}
	
}



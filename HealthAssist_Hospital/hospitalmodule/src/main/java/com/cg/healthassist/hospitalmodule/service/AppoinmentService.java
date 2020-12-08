package com.cg.healthassist.hospitalmodule.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.healthassist.hospitalmodule.domain.Appoinment;
import com.cg.healthassist.hospitalmodule.exception.AppoinmentIDException;
import com.cg.healthassist.hospitalmodule.exception.DoctorSpecializationException;
import com.cg.healthassist.hospitalmodule.repository.AppoinmentRepository;

/*
* Creating AppoinmentService class which provides different services for Appoinment class
*/
@Service
public class AppoinmentService {

	/*
	 * Declaring appoinmentRepository instance and autowired to instantiate the
	 * object
	 */
	@Autowired
	private AppoinmentRepository appoinmentRepository;

	public Appoinment saveOrUpdate(Appoinment appoinment) {

		try {
			appoinment.setAppoinmentId(appoinment.getAppoinmentId());
			return appoinmentRepository.save(appoinment);
		} catch (Exception e) {
			throw new AppoinmentIDException(
					"AppoinmentId " + appoinment.getAppoinmentId().toUpperCase() + " is already available");
		}
	}

	public List<Appoinment> findByDoctorId(String doctorId) {
		List<Appoinment> doctorList = new ArrayList<>();
		Iterable<Appoinment> appoinmentList = appoinmentRepository.findAll();
		for (Appoinment appoinment : appoinmentList) {
			if (appoinment.getDoctorId().equalsIgnoreCase(doctorId)) {
				doctorList.add(appoinment);
			}
		}
		if (doctorList.isEmpty()) {
			throw new DoctorSpecializationException("Doctor with " + doctorId + " not found");
		}
		return doctorList;

	}

}

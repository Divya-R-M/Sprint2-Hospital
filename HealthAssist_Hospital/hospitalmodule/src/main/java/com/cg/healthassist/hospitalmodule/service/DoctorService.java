package com.cg.healthassist.hospitalmodule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.healthassist.hospitalmodule.domain.Doctor;
import com.cg.healthassist.hospitalmodule.exception.DoctorIDException;
import com.cg.healthassist.hospitalmodule.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;
	
	public Doctor saveOrUpdate(Doctor doctor) {

		try {
			doctor.setDoctorId(doctor.getDoctorId());
			return doctorRepository.save(doctor);
		} catch (Exception e) {
			throw new DoctorIDException("DoctorId " + doctor.getDoctorId().toUpperCase() + " already available");
		}
	}

	public Doctor findDoctorByDoctorId(String doctorId) {
		Doctor doctor = doctorRepository.findByDoctorId(doctorId.toUpperCase());
		if (doctor == null) {
			throw new DoctorIDException("DoctorId " + doctorId + " is not available");
		}
		return doctor;

	}

	public Iterable<Doctor> findAllDoctors() {
		return doctorRepository.findAll();
	}

	public void deleteDoctorByDoctorId(String doctorId) {
		Doctor doctor = findDoctorByDoctorId(doctorId.toUpperCase());
		if (doctor == null) {
			throw new DoctorIDException("DoctorId " + doctorId + " is not available");
		}
		doctorRepository.delete(doctor);
	}
}

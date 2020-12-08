package com.cg.healthassist.hospitalmodule.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.healthassist.hospitalmodule.domain.Doctor;
import com.cg.healthassist.hospitalmodule.exception.DoctorIDException;
import com.cg.healthassist.hospitalmodule.exception.DoctorSpecializationException;
import com.cg.healthassist.hospitalmodule.repository.DoctorRepository;
/*
* Creating DoctorService class which provides different services for Doctor class
*/

@Service
public class DoctorService {

	/*
	 * Declaring doctorRepository instance and autowired to instantiate the object
	 */
	@Autowired
	private DoctorRepository doctorRepository;

	public Doctor saveDoctor(Doctor doctor) {

		try {
			doctor.setDoctorId(doctor.getDoctorId());
			return doctorRepository.save(doctor);
		} catch (Exception e) {
			throw new DoctorIDException("DoctorId " + doctor.getDoctorId() + " already available");
		}
	}

	public Doctor findDoctorByDoctorId(String doctorId) {
		Doctor doctor = doctorRepository.findByDoctorId(doctorId);
		if (doctor == null) {
			throw new DoctorIDException("DoctorId " + doctorId + " is not available");
		}
		return doctor;

	}

	public Iterable<Doctor> findAllDoctors() {
		return doctorRepository.findAll();
	}

	public boolean delete(String doctorId) {
		Doctor doctor = doctorRepository.findByDoctorId(doctorId);
		if (doctor == null) {
			throw new DoctorIDException("DoctorId " + doctorId + " is not available");
		}
		doctorRepository.delete(doctor);
		return true;
	}

	public Doctor updateDepartment(String doctorId, String department) {
		Doctor doctor = doctorRepository.findByDoctorId(doctorId);
		if (doctor == null) {
			throw new DoctorIDException("Doctor with " + doctorId + " is not available");
		}
		doctor.setDepartment(department);
		return doctorRepository.save(doctor);
	}

	public Doctor updatePhoneNo(String doctorId, Long doctorPhNo) {
		Doctor doctor = doctorRepository.findByDoctorId(doctorId);
		if (doctor == null) {
			throw new DoctorIDException("Doctor with " + doctorId + " is not available");
		}
		doctor.setDoctorPhNo(doctorPhNo);
		return doctorRepository.save(doctor);
	}

	public List<Doctor> findByDoctorSpecialization(String specialization) {
		List<Doctor> specializationList = new ArrayList<>();
		Iterable<Doctor> doctorList = doctorRepository.findAll();
		for (Doctor doctor : doctorList) {
			if (doctor.getSpecialization().equalsIgnoreCase(specialization)) {
				specializationList.add(doctor);
			}
		}
		if (specializationList.isEmpty()) {
			throw new DoctorSpecializationException("Doctor with " + specialization + " not found");
		}
		return specializationList;

	}
}

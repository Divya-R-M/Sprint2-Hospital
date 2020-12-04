package com.cg.healthassist.hospitalmodule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.healthassist.hospitalmodule.domain.Doctor;
import com.cg.healthassist.hospitalmodule.exception.DoctorIDException;
import com.cg.healthassist.hospitalmodule.exception.DoctorSpecializationException;
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
	
	public ResponseEntity<List<Doctor>> findByDoctorSpecialization(String specialization) throws DoctorSpecializationException {
		List<Doctor> list= doctorRepository.findByDoctorSpecialization(specialization);
			if(list.isEmpty())
			{
				throw new DoctorSpecializationException("Doctor Not found for " + specialization);
			}
			else
			{
				return  ResponseEntity.ok().body(list);
			}
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

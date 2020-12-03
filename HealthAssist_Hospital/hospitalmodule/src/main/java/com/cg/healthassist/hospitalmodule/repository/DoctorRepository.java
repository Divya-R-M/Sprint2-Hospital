package com.cg.healthassist.hospitalmodule.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.healthassist.hospitalmodule.domain.Doctor;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor,Long>{

	Doctor findByDoctorId(String doctorId);

}

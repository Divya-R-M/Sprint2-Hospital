package com.cg.healthassist.hospitalmodule.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.healthassist.hospitalmodule.domain.Doctor;
/*
 * creating DoctorRespository Interface which extends JpaRespository
 *  which provides CRUD operations
 *
 */
@Repository
public interface DoctorRepository extends CrudRepository<Doctor,Long>{

	Doctor findByDoctorId(String doctorId);

	@Query("SELECT c FROM Doctor c WHERE c.specialization = :specialization")
	List<Doctor> findByDoctorSpecialization(String specialization);

}

package com.cg.healthassist.hospitalmodule.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cg.healthassist.hospitalmodule.domain.Appoinment;
/*
 * creating AppoinmentRespository Interface which extends JpaRespository 
 * which provides CRUD operations
 */
public interface AppoinmentRepository extends CrudRepository<Appoinment,Long>{
	
	@Query("SELECT c FROM Appoinment c WHERE c.doctorId = :doctorId")
	public List<Appoinment> findByDoctorId(String doctorId);
}

package com.cg.healthassist.hospitalmodule.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.healthassist.hospitalmodule.domain.Bed;

@Repository
public interface BedRepository extends CrudRepository<Bed,Long>{

	Bed findByBedId(String bedId);
}

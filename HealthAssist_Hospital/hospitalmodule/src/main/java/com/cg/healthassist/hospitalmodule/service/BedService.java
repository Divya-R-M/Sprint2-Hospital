package com.cg.healthassist.hospitalmodule.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.healthassist.hospitalmodule.domain.Bed;
import com.cg.healthassist.hospitalmodule.exception.BedIDException;
import com.cg.healthassist.hospitalmodule.repository.BedRepository;

@Service
public class BedService {

	@Autowired
	private BedRepository bedRepository;

	public Bed saveOrUpdate(Bed bed) {

		try {
			bed.setBedId(bed.getBedId().toUpperCase());
			return bedRepository.save(bed);
		} catch (Exception e) {
			throw new BedIDException("BedId " + bed.getBedId() + "is already available");
		}
	}

	public Bed findBedByBedId(String bedId) {
		Bed bed = bedRepository.findByBedId(bedId.toUpperCase());
		if (bed == null) {
			throw new BedIDException("BedId " + bedId + " is not available");
		}
		return bed;

	}

	public Iterable<Bed> findAllBeds() {
		return bedRepository.findAll();
	}

	public void deleteBedByBedId(String bedId) {
		Bed bed = findBedByBedId(bedId.toUpperCase());
		if (bed == null) {
			throw new BedIDException("BedId " + bedId + " is not available");
		}
		bedRepository.delete(bed);
	}
	
	public Bed update(Bed bed) {

		try {
			bed.setBedId(bed.getBedId().toUpperCase());
			return bedRepository.save(bed);
		} catch (Exception e) {
			throw new BedIDException("BedId " + bed.getBedId() + "is already available");
		}
	}

}


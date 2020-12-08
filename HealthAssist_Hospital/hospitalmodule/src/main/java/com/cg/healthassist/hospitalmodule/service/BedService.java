package com.cg.healthassist.hospitalmodule.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.healthassist.hospitalmodule.domain.Bed;
import com.cg.healthassist.hospitalmodule.exception.BedIDException;
import com.cg.healthassist.hospitalmodule.repository.BedRepository;
/*
* Creating BedService class which provides different services for Bed class
*/

@Service
public class BedService {

	/*
	 * Declaring BedRepository instance and autowired to instantiate the object
	 */
	@Autowired
	private BedRepository bedRepository;

	public Bed save(Bed bed) {

		try {
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

	public List<Bed> findAllBeds() {
		List<Bed> bedList = (List<Bed>) bedRepository.findAll();
		if (bedList.isEmpty()) {
			throw new BedIDException("Bed records not available");
		}
		return bedList;
	}

	public boolean remove(String bedId) {
		Bed bed = bedRepository.findByBedId(bedId);
		if (bed == null) {
			throw new BedIDException("BedId " + bedId + " is not available");
		}
		bedRepository.delete(bed);
		return true;
	}

	public Bed updateBedType(String bedId, String bedType) {
		Bed bed = bedRepository.findByBedId(bedId);
		if (bed == null) {
			throw new BedIDException("Bed with " + bedId + " is not available");
		}
		bed.setBedType(bedType);
		return bedRepository.save(bed);
	}
}

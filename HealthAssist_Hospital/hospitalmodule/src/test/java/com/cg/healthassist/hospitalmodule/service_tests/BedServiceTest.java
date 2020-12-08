package com.cg.healthassist.hospitalmodule.service_tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.healthassist.hospitalmodule.domain.Bed;
import com.cg.healthassist.hospitalmodule.repository.BedRepository;
import com.cg.healthassist.hospitalmodule.service.BedService;

/*
 * BedServiceTest class contains test methods for for Bed Service
 */
@ExtendWith(MockitoExtension.class)
class BedServiceTest {
	@Mock
	private BedRepository bedRepository;

	@InjectMocks
	private BedService bedService;

	/*
	 * Test for save method in Bed Service
	 */
	@Test
	public void test_Save_GivenBed() {
		Bed newbed = new Bed("BB10", "Manual");
		when(bedRepository.save(Mockito.any(Bed.class))).thenReturn(newbed);
		Bed bedFound = bedService.save(newbed);
		assertEquals("Manual", bedFound.getBedType());
	}

	/*
	 * Test for findAllBeds method in Bed Service
	 */
	@Test
	public void test_FindAll_ListOfBeds() {
		List<Bed> list = new LinkedList<Bed>();
		list.add(new Bed("BB11", "Manual"));
		list.add(new Bed("BB12", "Electric"));
		when(bedRepository.findAll()).thenReturn(list);
		Iterable<Bed> result = bedService.findAllBeds();
		assertEquals(list.size(), ((List<Bed>) result).size());
	}

	/*
	 * Test for remove method in Bed Service
	 */
	@Test
	public void test_RemoveBed_GivenByBedId() {
		Bed newbed = new Bed("BB06", "Low electric");
		Mockito.when(bedRepository.findByBedId("BB06")).thenReturn(newbed).thenReturn(null);
		boolean result = bedService.remove("BB06");
		assertThat(result);
	}

	/*
	 * Test for updateBedType method in Bed Service
	 */
	@Test
	public void test_UpdateBedType_GivenByBedType() {
		Bed bed = new Bed("BB07", "Electric");
		when(bedRepository.findByBedId("BB07")).thenReturn(bed);
		when(bedRepository.save(Mockito.any(Bed.class))).thenReturn(bed);
		Bed newbed = bedService.updateBedType("BB07", "Manual");
		assertEquals("Manual", newbed.getBedType());
	}

	/*
	 * Test for findBedByBedId method in Bed Service
	 */
	@Test
	public void test_FindBed_ByBedId() {
		Bed newbed = new Bed("BB06", "Low electric");
		Mockito.when(bedRepository.findByBedId("BB06")).thenReturn(newbed).thenReturn(null);
		Bed result = bedService.findBedByBedId("BB06");
		assertThat(result);
	}

}

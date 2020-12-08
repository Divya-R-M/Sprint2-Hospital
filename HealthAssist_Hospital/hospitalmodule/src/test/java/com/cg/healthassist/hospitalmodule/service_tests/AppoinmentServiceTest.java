package com.cg.healthassist.hospitalmodule.service_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.cg.healthassist.hospitalmodule.domain.Appoinment;
import com.cg.healthassist.hospitalmodule.repository.AppoinmentRepository;
import com.cg.healthassist.hospitalmodule.service.AppoinmentService;
/*
 * AppoinmentServiceTest class contains test methods for for Appoinment Service
 */
@ExtendWith(MockitoExtension.class)
class AppoinmentServiceTest {
	@Mock
	private AppoinmentRepository appoinmentRepository;

	@InjectMocks
	private AppoinmentService appoinmentService;

	/*
	 * Test for saveOrUpdate method in Appoinment Service
	 */
	@Test
	public void test_Save_GivenAppoinment() {
		Appoinment newAppoinment = new Appoinment("AA10", "DD33", "Suresh", "Diabetes", "12/12/2019", "12:30 PM");
		when(appoinmentRepository.save(Mockito.any(Appoinment.class))).thenReturn(newAppoinment);
		Appoinment appFound = appoinmentService.saveOrUpdate(newAppoinment);
		assertEquals("AA10", appFound.getAppoinmentId());
	}

	/*
	 * Test for findByDoctorId method in Appoinment Service
	 */
	@Test
	public void test_FindByDoctoId_ReturnAppoinmentObject() {
		List<Appoinment> list = new ArrayList<>();
		list.add(new Appoinment("AA10", "DD33", "Suresh", "Diabetes", "12/12/2019", "12:30 PM"));
		list.add(new Appoinment("AA11", "DD33", "Shiva", "Hypertension", "12/12/2019", "01:00 PM"));
		list.add(new Appoinment("AA12", "DD34", "Hema", "Opthalmology", "12/12/2019", "01:30 PM"));
		when(appoinmentRepository.findAll()).thenReturn(list);
		List<Appoinment> result = appoinmentService.findByDoctorId("DD33");
		assertEquals(2, result.size());
	}
}

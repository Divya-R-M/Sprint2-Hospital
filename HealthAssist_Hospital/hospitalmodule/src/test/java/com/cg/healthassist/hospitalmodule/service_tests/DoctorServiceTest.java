package com.cg.healthassist.hospitalmodule.service_tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.healthassist.hospitalmodule.domain.Doctor;
import com.cg.healthassist.hospitalmodule.repository.DoctorRepository;
import com.cg.healthassist.hospitalmodule.service.DoctorService;

/*
 * DoctorServiceTest class contains test methods for for Doctor Service
 */
@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {
	@Mock
	private DoctorRepository doctorRepository;

	@InjectMocks
	private DoctorService doctorService;

	/*
	 * Test for saveDoctor method in Doctor Service
	 */
	@Test
	public void test_Save_GivenDoctor() {
		Doctor newdoctor = new Doctor("Namrata", "DD05", "Heart", "Surgery", 70858653344L);
		when(doctorRepository.save(Mockito.any(Doctor.class))).thenReturn(newdoctor);
		Doctor doctorFound = doctorService.saveDoctor(newdoctor);
		assertEquals("Namrata", doctorFound.getDoctorName());
	}

	/*
	 * Test for findAllDoctors method in Doctor Service
	 */
	@Test
	public void test_FindAll_AvailableDoctors() {
		List<Doctor> list = new LinkedList<>();
		list.add(new Doctor("Aastha", "DD02", "Heart", "Surgery", 9846533622L));
		list.add(new Doctor("Ram", "DD03", "Eye", "Opthalmology", 9856251523L));
		when(doctorRepository.findAll()).thenReturn(list);
		Iterable<Doctor> result = doctorService.findAllDoctors();
		assertEquals(list.size(), ((List<Doctor>) result).size());
	}

	/*
	 * Test for delete method in Doctor Service
	 */
	@Test
	public void test_RemoveDoctor_GivenByDoctorId() {
		Doctor newdoctor = new Doctor("Shruti", "DD06", "skin", "dermatology", 8742344444L);
		Mockito.when(doctorRepository.findByDoctorId("DD06")).thenReturn(newdoctor).thenReturn(null);
		boolean result = doctorService.delete("DD06");
		assertThat(result);

	}

	/*
	 * Test for updateDepartment method in Doctor Service
	 */
	@Test
	public void test_UpdateDepartment_GivenByDepartment() {
		Doctor doctor = new Doctor("Ram", "DD03", "eye", "Opthalmology", 9856251523L);
		when(doctorRepository.findByDoctorId("DD03")).thenReturn(doctor);
		when(doctorRepository.save(Mockito.any(Doctor.class))).thenReturn(doctor);
		Doctor newDoctor = doctorService.updateDepartment("DD03", "Gynecology");
		assertEquals("Gynecology", newDoctor.getDepartment());
	}

	/*
	 * Test for updatePhoneNo method in Doctor Service
	 */
	@Test
	public void test_UpdatePhoneNumber_GivenDoctorPhoneNumber() {
		Doctor doctor = new Doctor("Shyam", "DD07", "Heart", "Surgery", 7756441523L);
		when(doctorRepository.findByDoctorId("DD07")).thenReturn(doctor);
		when(doctorRepository.save(Mockito.any(Doctor.class))).thenReturn(doctor);
		Doctor newDoctor = doctorService.updatePhoneNo("DD07", 7028485794L);
		assertEquals(7028485794L, newDoctor.getDoctorPhNo());
	}

	/*
	 * Test for findByDoctorSpecialization method in Doctor Service
	 */
	@Test
	public void test_FindBySpecialization_ReturnDoctorObject() {
		List<Doctor> list = new ArrayList<>();
		list.add(new Doctor("Ram", "DD03", "eye", "Opthalmology", 9856251523L));
		list.add(new Doctor("Shyam", "DD07", "Heart", "Surgery", 7756441523L));
		list.add(new Doctor("Shiva", "DD10", "Heart", "Surgery", 6543678899L));
		when(doctorRepository.findAll()).thenReturn(list);
		List<Doctor> result = doctorService.findByDoctorSpecialization("Heart");
		assertEquals(2, result.size());
	}

	/*
	 * Test for findDoctorByDoctorId method in Doctor Service
	 */
	@Test
	public void test_FindDoctor_ByDoctorId() {
		Doctor newdoctor = new Doctor("Shruti", "DD06", "skin", "dermatology", 8742344444L);
		Mockito.when(doctorRepository.findByDoctorId("DD06")).thenReturn(newdoctor).thenReturn(null);
		Doctor result = doctorService.findDoctorByDoctorId("DD06");
		assertThat(result);
	}

}

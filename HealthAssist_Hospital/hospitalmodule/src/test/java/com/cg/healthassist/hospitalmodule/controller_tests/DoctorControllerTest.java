package com.cg.healthassist.hospitalmodule.controller_tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.cg.healthassist.hospitalmodule.domain.Doctor;
import com.cg.healthassist.hospitalmodule.service.DoctorService;
import com.cg.healthassist.hospitalmodule.service.MapValidationErrorService;
import com.cg.healthassist.hospitalmodule.web.DoctorController;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * DoctorControllerTest class contains test methods for Doctor Controller
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DoctorController.class)
class DoctorControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	DoctorService doctorService;

	@MockBean
	MapValidationErrorService mapValidationErrorService;

	private static ObjectMapper mapper = new ObjectMapper();

	/*
	 * Test for saveDoctor method in Doctor controller where the HTTP status is
	 * checked with actual and expected value
	 */
	@Test
	public void test_AddDoctor_ReturnsDoctorJson() throws Exception {
		Doctor newdoctor = new Doctor("Namrata", "DD05", "Heart", "Surgery", 70858653344L);
		Mockito.when(doctorService.saveDoctor(ArgumentMatchers.any())).thenReturn(newdoctor);
		String json = mapper.writeValueAsString(newdoctor);
		mockMvc.perform(post("/doctors").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(status().isCreated()).andExpect(jsonPath("$").isMap())
				.andExpect(jsonPath("doctorName").value("Namrata")).andExpect(jsonPath("doctorId").value("DD05"))
				.andExpect(jsonPath("specialization").value("Heart")).andExpect(jsonPath("department").value("Surgery"))
				.andExpect(jsonPath("doctorPhNo").value(70858653344L));

	}

	/*
	 * Test for findDoctorByDoctorId method in Doctor controller where the HTTP
	 * status is checked with actual and expected value
	 */
	@Test
	public void test_FindByDoctorId_ReturnsDoctorJson() throws Exception {
		Doctor newDoctor = new Doctor("Namrata", "DD05", "Heart", "Surgery", 70858653344L);
		Mockito.when(doctorService.findDoctorByDoctorId("DD05")).thenReturn(newDoctor);
		mockMvc.perform(get("/doctors/ById/DD05")).andExpect(status().isOk())
				.andExpect(jsonPath("doctorId", Matchers.equalTo("DD05")));
	}

	/*
	 * Test for findAllDoctors method in Doctor controller where the HTTP status is
	 * checked with actual and expected value
	 */
	@Test
	public void test_FindAll_ReturnListOfAllDoctors() throws Exception {
		List<Doctor> list = new ArrayList<>();
		list.add(new Doctor("Aastha", "DD02", "Heart", "Surgery", 9846533622L));
		list.add(new Doctor("Ram", "DD03", "Eye", "Opthalmology", 9856251523L));
		list.add(new Doctor("Namrata", "DD05", "Heart", "Surgery", 70858653344L));
		Mockito.when(doctorService.findAllDoctors()).thenReturn(list);
		mockMvc.perform(get("/doctors/all")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(3)))
				.andExpect(jsonPath("$[0].doctorName", Matchers.equalTo("Aastha")));

	}

	/*
	 * Test for findByDoctorSpecialization method in Doctor controller where the
	 * HTTP status is checked with actual and expected value
	 */
	@Test
	public void test_FindBySpecialization_ReturnsListOfDoctors() throws Exception {
		List<Doctor> list = new ArrayList<>();
		List<Doctor> specList = new ArrayList<>();
		list.add(new Doctor("Aastha", "DD02", "Heart", "Surgery", 9846533622L));
		list.add(new Doctor("Ram", "DD03", "Eye", "Opthalmology", 9856251523L));
		list.add(new Doctor("Namrata", "DD05", "Heart", "Surgery", 70858653344L));
		for (Doctor d : list) {
			if (d.getSpecialization().equalsIgnoreCase("heart")) {
				specList.add(d);
			}
		}
		Mockito.when(doctorService.findByDoctorSpecialization("Eye")).thenReturn(specList);
		mockMvc.perform(get("/doctors/ByCategory/Eye")).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[0].doctorName", Matchers.equalTo("Aastha")));
	}

	/*
	 * Test for delete method in Doctor controller where the HTTP status is checked
	 * with actual and expected value
	 */
	@Test
	public void test_DeleteById_ReturnsBooleanValue() throws Exception {
		Doctor newDoctor = new Doctor("Ram", "DD03", "Eye", "Opthalmology", 9856251523L);
		Mockito.when(doctorService.delete("DD03")).thenReturn(true);
		mockMvc.perform(delete("/doctors/delete/DD03")).andExpect(status().isOk()).andExpect(
				jsonPath("$", Matchers.anything("Deleted Doctor details with Id: " + newDoctor.getDoctorId() + "!")));
	}

	@Test
	public void test_UpdateByDepartment_ReturnsUpdateDoctorDetails() throws Exception {
		Doctor doctor = new Doctor("Shruti", "DD06", "skin", "dermatology", 8742344444L);
		Mockito.when(doctorService.updateDepartment("DD06", "skin")).thenReturn(doctor);
		doctor.setDepartment("skin");
		String json = mapper.writeValueAsString(doctor);
		mockMvc.perform(get("/doctors/updateDepartment/DD06/skin").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("department").value("skin"));
	}

	/*
	 * Test for updatePhoneNo method in Doctor controller where the HTTP status is
	 * checked with actual and expected value
	 */
	@Test
	public void test_UpdatePhoneNumber_ReturnsUpdateDoctorDetails() throws Exception {
		Doctor doctor = new Doctor("Namrata", "DD05", "Heart", "Surgery", 70858653344L);
		Mockito.when(doctorService.updatePhoneNo("DD05", 8765445789L)).thenReturn(doctor);
		doctor.setDoctorPhNo(8765445789L);
		String json = mapper.writeValueAsString(doctor);
		mockMvc.perform(get("/doctors/updatePhoneNo/DD05/8765445789").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("doctorPhNo").value(8765445789L));
	}

}

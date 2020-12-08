package com.cg.healthassist.hospitalmodule.controller_tests;

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

import com.cg.healthassist.hospitalmodule.domain.Appoinment;
import com.cg.healthassist.hospitalmodule.service.AppoinmentService;
import com.cg.healthassist.hospitalmodule.service.MapValidationErrorService;
import com.cg.healthassist.hospitalmodule.web.AppoinmentController;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * AppoinmentControllerTest class contains test methods for for Appoinment Controller
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AppoinmentController.class)
class AppoinmentControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	AppoinmentService appoinmentService;

	@MockBean
	MapValidationErrorService mapValidationErrorService;

	private static ObjectMapper mapper = new ObjectMapper();

	/*
	 * Test for saveOrUpdate method in Appoinment controller where the HTTP status
	 * is checked with actual and expected value
	 */
	@Test
	public void test_AddAppoinment_ReturnsAppoinmentJson() throws Exception {
		Appoinment newAppoinment = new Appoinment("AA10", "DD33", "Suresh", "Diabetes", "12-12-2019", "12:30PM");
		Mockito.when(appoinmentService.saveOrUpdate(ArgumentMatchers.any())).thenReturn(newAppoinment);
		String json = mapper.writeValueAsString(newAppoinment);
		mockMvc.perform(post("/appoinments/add").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(status().isCreated()).andExpect(jsonPath("$").isMap())
				.andExpect(jsonPath("appoinmentId").value("AA10")).andExpect(jsonPath("doctorId").value("DD33"))
				.andExpect(jsonPath("patientName").value("Suresh")).andExpect(jsonPath("diagnosis").value("Diabetes"))
				.andExpect(jsonPath("date").value("12-12-2019")).andExpect(jsonPath("time").value("12:30PM"));

	}

	/*
	 * Test for findByDoctorId method in Appoinment controller where the HTTP status
	 * is checked with actual and expected value
	 */
	@Test
	public void test_FindByDoctorId_ReturnsListOfAppoinment() throws Exception {
		List<Appoinment> list = new ArrayList<>();
		List<Appoinment> appList = new ArrayList<>();
		list.add(new Appoinment("AA10", "DD33", "Suresh", "Diabetes", "12/12/2019", "12:30 PM"));
		list.add(new Appoinment("AA11", "DD33", "Shiva", "Hypertension", "12/12/2019", "01:00 PM"));
		list.add(new Appoinment("AA12", "DD34", "Hema", "Opthalmology", "12/12/2019", "01:30 PM"));
		for (Appoinment app : list) {
			if (app.getDoctorId().equalsIgnoreCase("dd33")) {
				appList.add(app);
			}
		}
		Mockito.when(appoinmentService.findByDoctorId("DD33")).thenReturn(appList);
		mockMvc.perform(get("/appoinments/get/DD33")).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[0].doctorId", Matchers.equalTo("DD33")));
	}

}
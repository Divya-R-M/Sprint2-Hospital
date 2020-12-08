package com.cg.healthassist.hospitalmodule.controller_tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.cg.healthassist.hospitalmodule.domain.PatientTreatmentHistory;
import com.cg.healthassist.hospitalmodule.service.MapValidationErrorService;
import com.cg.healthassist.hospitalmodule.service.PatientTreatmentHistoryService;
import com.cg.healthassist.hospitalmodule.web.PatientTreatmentHistoryController;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * PatientTreatmentHistoryControllerTest class contains test methods for PatientTreatmentHistory Controller
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PatientTreatmentHistoryController.class)
public class PatientTreatmentHistoryControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	PatientTreatmentHistoryService patientTreatmentHistoryService;

	@MockBean
	MapValidationErrorService mapValidationErrorService;

	private static ObjectMapper mapper = new ObjectMapper();

	/*
	 * Test for savePatientTreatmentHistory method in PatientTreatmentHistory
	 * controller where the HTTP status is checked with actual and expected value
	 */
	@Test
	public void test_AddPatientHistory_ReturnsPatientsTreatmentHistoryJson() throws Exception {
		PatientTreatmentHistory patientTreatmentHistory = new PatientTreatmentHistory("Radha", "PP13", 23, "Anxiety",
				"Niraj", "2020-10-24");
		Mockito.when(patientTreatmentHistoryService.savePatientTreatmentHistory(ArgumentMatchers.any()))
				.thenReturn(patientTreatmentHistory);
		String json = mapper.writeValueAsString(patientTreatmentHistory);
		mockMvc.perform(post("/patientTreatmentHistory").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(status().isCreated()).andExpect(jsonPath("$").isMap())
				.andExpect(jsonPath("patientName").value("Radha")).andExpect(jsonPath("patientId").value("PP13"))
				.andExpect(jsonPath("patientAge").value(23)).andExpect(jsonPath("diagnosis").value("Anxiety"))
				.andExpect(jsonPath("doctorName").value("Niraj"))
				.andExpect(jsonPath("treatmentDate").value("2020-10-24"));
	}

	/*
	 * Test for updateByDiagnosis method in PatientTreatmentHistory controller where
	 * the HTTP status is checked with actual and expected value
	 */
	@Test
	public void test_UpdateByDiagnosis_ReturnsUpdatedPatientTreatmentDetails() throws Exception {
		PatientTreatmentHistory patientTreatmentHistory = new PatientTreatmentHistory("Riya", "PP11", 37,
				"Hypertension", "Nirja", "2020-11-22");
		Mockito.when(patientTreatmentHistoryService.updateByDiagnosis("PP11", "Diabetes"))
				.thenReturn(patientTreatmentHistory);
		patientTreatmentHistory.setDiagnosis("Diabetes");
		String json = mapper.writeValueAsString(patientTreatmentHistory);
		mockMvc.perform(
				get("/patientTreatmentHistory/byDiagnosis/PP11/Diabetes").contentType(MediaType.APPLICATION_JSON)
						.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("diagnosis").value("Diabetes"));
	}

	/*
	 * Test for updateByTreatmentDate method in PatientTreatmentHistory controller
	 * where the HTTP status is checked with actual and expected value
	 */
	@Test
	public void test_UpdateByTreatmentDate_ReturnsUpdatedPatientTreatmentDetails() throws Exception {
		PatientTreatmentHistory patientTreatmentHistory = new PatientTreatmentHistory("Riya", "PP11", 37,
				"Hypertension", "Nirja", "2020-11-22");
		Mockito.when(patientTreatmentHistoryService.updateByTreatmentDate("PP11", "2020-09-25"))
				.thenReturn(patientTreatmentHistory);
		patientTreatmentHistory.setTreatmentDate("2020-09-25");
		String json = mapper.writeValueAsString(patientTreatmentHistory);
		mockMvc.perform(
				get("/patientTreatmentHistory/byTreatmentDate/PP11/2020-09-25").contentType(MediaType.APPLICATION_JSON)
						.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("treatmentDate").value("2020-09-25"));
	}

}

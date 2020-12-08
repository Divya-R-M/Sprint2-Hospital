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

import com.cg.healthassist.hospitalmodule.domain.Bed;
import com.cg.healthassist.hospitalmodule.service.BedService;
import com.cg.healthassist.hospitalmodule.service.MapValidationErrorService;
import com.cg.healthassist.hospitalmodule.web.BedController;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * BedControllerTest class contains test methods for Bed Controller
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BedController.class)
class BedControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	BedService bedService;

	@MockBean
	MapValidationErrorService mapValidationErrorService;

	private static ObjectMapper mapper = new ObjectMapper();

	/*
	 * Test for save method in Bed controller where the HTTP status is checked with
	 * actual and expected value
	 */
	@Test
	public void test_AddBed_ReturnsBedJson() throws Exception {
		Bed newBed = new Bed("BB101", "Manual");
		Mockito.when(bedService.save(ArgumentMatchers.any())).thenReturn(newBed);
		String json = mapper.writeValueAsString(newBed);
		mockMvc.perform(post("/beds").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andExpect(status().isCreated())
				.andExpect(jsonPath("$").isMap()).andExpect(jsonPath("bedId").value("BB101"))
				.andExpect(jsonPath("bedType").value("Manual"));
	}

	/*
	 * Test for findBedByBedId method in Bed controller where the HTTP status is
	 * checked with actual and expected value
	 */
	@Test
	public void test_FindByBedId_ReturnsBedJson() throws Exception {
		Bed newBed = new Bed("BB111", "Electric");
		Mockito.when(bedService.findBedByBedId("BB111")).thenReturn(newBed);
		mockMvc.perform(get("/beds/get/BB111")).andExpect(status().isOk())
				.andExpect(jsonPath("bedId", Matchers.equalTo("BB111")));
	}

	/*
	 * Test for findAllBeds method in Bed controller where the HTTP status is
	 * checked with actual and expected value
	 */
	@Test
	public void test_FindAll_ReturnListOfAllBeds() throws Exception {
		List<Bed> list = new ArrayList<>();
		list.add(new Bed("BB102", "Electric"));
		list.add(new Bed("BB103", "Manual"));
		list.add(new Bed("BB104", "Low Electric"));
		Mockito.when(bedService.findAllBeds()).thenReturn(list);
		mockMvc.perform(get("/beds/all")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(3)));
	}

	/*
	 * Test for remove method in Bed controller where the HTTP status is checked
	 * with actual and expected value
	 */
	@Test
	public void test_DeleteByBedId_ReturnsBooleanValue() throws Exception {
		Bed newBed = new Bed("BB106", "Low electric");
		Mockito.when(bedService.remove("BB106")).thenReturn(true);
		mockMvc.perform(delete("/beds/remove/BB106")).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.anything("Deleted Bed type with Id: " + newBed.getBedId() + "!")));
	}

	/*
	 * Test for updateBedType method in Bed controller where the HTTP status is
	 * checked with actual and expected value
	 */
	@Test
	public void test_UpdateByBedType_ReturnsUpdateBedType() throws Exception {
		Bed bed = new Bed("BB107", "Electric");
		Mockito.when(bedService.updateBedType("BB107", "Manual")).thenReturn(bed);
		bed.setBedType("Manual");
		String json = mapper.writeValueAsString(bed);
		mockMvc.perform(get("/beds/updateBedType/BB107/Manual").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("bedType").value("Manual"));
	}

}

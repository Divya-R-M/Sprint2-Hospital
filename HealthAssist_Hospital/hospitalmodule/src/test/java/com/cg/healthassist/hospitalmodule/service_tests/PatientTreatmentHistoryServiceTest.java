package com.cg.healthassist.hospitalmodule.service_tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.healthassist.hospitalmodule.domain.PatientTreatmentHistory;
import com.cg.healthassist.hospitalmodule.repository.PatientTreatmentHistoryRepository;
import com.cg.healthassist.hospitalmodule.service.PatientTreatmentHistoryService;

/*
 * PatientHistoryServiceTest class contains test methods for for PatientHistoryService Service
 */
@ExtendWith(MockitoExtension.class)
class PatientHistoryServiceTest {

	@Mock
	private PatientTreatmentHistoryRepository patientTreatmentHistoryRepository;

	@InjectMocks
	private PatientTreatmentHistoryService patientTreatmentHistoryService;

	/*
	 * Test for savePatientTreatmentHistory method in PatientTreatmentHistory
	 * Service
	 */
	@Test
	public void test_Save_GienTreatmentHistoryObject() {
		PatientTreatmentHistory patientTreatmentHistory = new PatientTreatmentHistory("PP10", "Sunil", 20, "Diabetes",
				"Sonal", "2020-11-20");
		when(patientTreatmentHistoryRepository.save(Mockito.any(PatientTreatmentHistory.class)))
				.thenReturn(patientTreatmentHistory);
		PatientTreatmentHistory historyFound = patientTreatmentHistoryService
				.savePatientTreatmentHistory(patientTreatmentHistory);
		assertEquals(patientTreatmentHistory.getPatientName(), historyFound.getPatientName());
	}

	/*
	 * Test for updateByDiagnosis method in PatientTreatmentHistory Service
	 */
	@Test
	public void test_UpdateByDiagnosis_GivenPatientId() {
		PatientTreatmentHistory patientTreatmentHistory = new PatientTreatmentHistory("PP11", "Riya", 33,
				"Hypertension", "Nirja", "2020-11-22");
		when(patientTreatmentHistoryRepository.findByPatientId("PP11")).thenReturn(patientTreatmentHistory);
		when(patientTreatmentHistoryRepository.save(Mockito.any(PatientTreatmentHistory.class)))
				.thenReturn(patientTreatmentHistory);
		PatientTreatmentHistory resultTest = patientTreatmentHistoryService.updateByDiagnosis("PP11", "Skin");
		assertEquals("Skin", resultTest.getDiagnosis());
	}

	/*
	 * Test for updateByTreatmentDate method in PatientTreatmentHistory Service
	 */
	@Test
	public void test_UpdateByTreatmentDate_GivenPatientId() {
		PatientTreatmentHistory patientTreatmentHistory = new PatientTreatmentHistory("PP13", "Radha", 13, "Anxiety",
				"Niraj", "2020-10-24");
		when(patientTreatmentHistoryRepository.findByPatientId("PP13")).thenReturn(patientTreatmentHistory);
		when(patientTreatmentHistoryRepository.save(Mockito.any(PatientTreatmentHistory.class)))
				.thenReturn(patientTreatmentHistory);
		PatientTreatmentHistory resultTest = patientTreatmentHistoryService.updateByTreatmentDate("PP13", "2020-12-02");
		assertEquals("2020-12-02", resultTest.getTreatmentDate());
	}

}

package com.cg.healthassist.hospitalmodule.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
/*
 * Patient domain will be used as data traveller object.
 * All Project Validation will be performed here.
 * @Author Divya and Charushi
 */
@Entity
public class PatientTreatmentHistory {

	/*
	 * creating instance variables for PatientTreatmentHistory class
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long historyId;
	@NotBlank(message = "PatientName is required")
	private String patientName;
	@NotBlank(message = "PatientId is required")
	@Column(unique = true, updatable = false)
	private String patientId;
	@NotNull(message = "PatientAge is required")
	private int patientAge;
	@NotBlank(message = "Diagnosis is required")
	private String diagnosis;
	@NotBlank(message = "DoctorName is required")
	private String doctorName;
	@NotBlank(message = "TreatmentDate is required")
	private String treatmentDate;
	
	/*
	 * creating non-parameterized constructor 
	 */
	
	public PatientTreatmentHistory() {
		super();
	}

	/*
	 *  creating a parameterized constructor to set the values passed as parameters to respective instance variables while creating constructor object 
	 */
	public PatientTreatmentHistory( String patientName, String patientId, int patientAge, String diagnosis,String doctorName, String treatmentDate) {
		super();
		this.patientName = patientName;
		this.patientId = patientId;
		this.patientAge = patientAge;
		this.diagnosis = diagnosis;
		this.doctorName = doctorName;
		this.treatmentDate = treatmentDate;
	}

	/*
	 * Getters and Setter for PatientTreatmentHistory class
	 */
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public int getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getTreatmentDate() {
		return treatmentDate;
	}
	public void setTreatmentDate(String treatmentDate) {
		this.treatmentDate = treatmentDate;
	}

	
}
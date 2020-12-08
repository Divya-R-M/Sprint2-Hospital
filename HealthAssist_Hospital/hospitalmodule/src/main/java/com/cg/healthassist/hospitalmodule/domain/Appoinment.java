package com.cg.healthassist.hospitalmodule.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/*
 * Appoinment domain will be used as data traveller object.
 * All Project Validation will be performed here.
 * @Author Divya and Charushi
 */
@Entity
public class Appoinment {

	/**
	 * creating instance variables for Appoinment class
	 */
	@Id
	private String appoinmentId;
	@NotBlank(message = "DoctorId is required")
	private String doctorId;
	@NotBlank(message = "PatientName is required")
	private String patientName;
	@NotBlank(message = "Diagnosis is required")
	private String diagnosis;
	@NotBlank(message = "Date is required")
	private String date;
	@NotBlank(message = "Time is required")
	private String time;

	/*
	 * creating non-parameterized constructor
	 */

	public Appoinment() {
		super();
	}

	/*
	 * creating a parameterized constructor to set the values passed as parameters
	 * to respective instance variables while creating constructor object
	 */
	public Appoinment(String appoinmentId, String doctorId, String patientName, String diagnosis, String date,
			String time) {
		super();
		this.appoinmentId = appoinmentId;
		this.doctorId = doctorId;
		this.patientName = patientName;
		this.diagnosis = diagnosis;
		this.date = date;
		this.time = time;
	}

	/*
	 * Getters and Setter for Appoinment class
	 */

	public String getAppoinmentId() {
		return appoinmentId;
	}

	public void setAppoinmentId(String appoinmentId) {
		this.appoinmentId = appoinmentId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

//    @ManyToOne()
//    @JoinColumn(name="doctor_Id")
//    private Doctor doctor;

//	public Doctor getDoctor() {
//		return doctor;
//	}
//
//	public void setDoctor(Doctor doctor) {
//		this.doctor = doctor;
//	}

}

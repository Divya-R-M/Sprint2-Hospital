package com.cg.healthassist.hospitalmodule.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * Doctor domain will be used as data traveller object.
 * All Project Validation will be performed here.
 * @Author Divya and Charushi
 */
@Entity
public class Doctor {
     
	/*
	 * creating instance variables for Doctor class
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "DoctorName is required")
	private String doctorName;
	@NotBlank(message = "DoctorId is required")
	@Size(min = 3, max = 5, message = "size must be between 3 and 5 characters")
	@Column(unique = true, updatable = false)
	private String doctorId;
	@NotBlank(message = "DoctorSpecialization is required")
	private String specialization;
	@NotBlank(message = "DoctorDepartment is required")
	private String department;
	@NotNull(message = "DoctorPhNo is required")
	private long doctorPhNo;
	
	/*
	 * creating non-parameterized constructor 
	 */
	
	public Doctor() {
		super();
	}

	/*
	 *  creating a parameterized constructor to set the values passed as parameters to respective instance variables while creating constructor object 
	 */
	public Doctor(String doctorName, String doctorId, String specialization, String department, long doctorPhNo) {
		super();
		this.doctorName = doctorName;
		this.doctorId = doctorId;
		this.specialization = specialization;
		this.department = department;
		this.doctorPhNo = doctorPhNo;
	}

	/*
	 * Getters and Setter for Doctor class
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public long getDoctorPhNo() {
		return doctorPhNo;
	}

	public void setDoctorPhNo(long doctorPhNo) {
		this.doctorPhNo = doctorPhNo;
	}

//	public List<Appoinment> getAppoinments() {
//		return appoinments;
//	}
//
//	public void setAppoinments(List<Appoinment> appoinments) {
//		this.appoinments = appoinments;
//	}

    

}

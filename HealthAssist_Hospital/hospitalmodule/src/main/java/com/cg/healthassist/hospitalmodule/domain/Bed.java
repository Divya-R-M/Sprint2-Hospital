package com.cg.healthassist.hospitalmodule.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/*
 * Bed domain will be used as data traveller object.
 * All Project Validation will be performed here.
 * @Author Divya and Charushi
 */
@Entity
public class Bed {

	/*
	 * creating instance variables for Bed class
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotBlank(message = "BedId is required")
	@Size(min = 3, max = 5, message = "size must be between 3 and 5 characters")
	@Column(unique = true, updatable = false)
	private String bedId;
	@NotBlank(message = "BedType is required")
	private String bedType;

	/*
	 * creating non-parameterized constructor
	 */

	public Bed() {
		super();
	}

	/*
	 * creating a parameterized constructor to set the values passed as parameters
	 * to respective instance variables while creating constructor object
	 */

	public Bed(String bedId, String bedType) {
		super();
		this.bedId = bedId;
		this.bedType = bedType;
	}

	/*
	 * Getters and Setter for Bed class
	 */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBedId() {
		return bedId;
	}

	public void setBedId(String bedId) {
		this.bedId = bedId;
	}

	public String getBedType() {
		return bedType;
	}

	public void setBedType(String bedType) {
		this.bedType = bedType;
	}

}

package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// Declare la classe employe comme entite a mapper en db
@Entity
public class Employe {

	// Declare cette attribut comme Primary Key
	@Id
	// AUTO_INCREMENT
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPID")
	private Integer empId;
	@Column(name = "FIRSTNAME")
	private String firstName;
	@Column(name = "LASTNAME")
	private String lastName;

	public Employe() {
		super();
	}

	public Employe(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Employe(Integer empId, String firstName, String lastName) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Employe [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}

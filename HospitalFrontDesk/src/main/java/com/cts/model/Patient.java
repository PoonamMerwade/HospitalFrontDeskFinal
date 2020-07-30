package com.cts.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity(name="patient")
public class Patient implements Comparable<Patient> {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int patientId;
	private String patientName;
	private int age;
	private String patientStatus;
	private String mobileNumber;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="hospitalId")
	private Hospital hospital;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="specialistId")
	private Specialist specialist;
	
	
	public Patient() {
		super();
	}
	public Patient(int patientId, String patientName, int age, String patientStatus, String mobileNumber,Hospital hospital) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.age = age;
		this.patientStatus = patientStatus;
		this.mobileNumber = mobileNumber;
		this.hospital=hospital;
	}
	@Override
	public int compareTo(Patient o) {
		return (this.patientId<o.patientId?-1:(this.patientId>o.patientId?1:0));
	}	
}

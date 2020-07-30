package com.cts.model;

import java.util.List;

//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity(name="specialist")
public class Specialist implements Comparable<Specialist> {
	

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int specialistId;
	
	private String specialistName;
	
	private String type;
	
	private String availableTime;
	
	private String availableDay;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="hospitalId")
	private Hospital hospital;
	
	@JsonBackReference
	@OneToMany
//	@JoinColumn(name="patientId")
	private List<Patient> patient;
	
	public Specialist() {
		super();
	}
	public Specialist(int specialistId, String specialistName, String type, String availableTime, String availableDay,Hospital hospital,List<Patient> patient) {
		super();
		this.specialistId = specialistId;
		this.specialistName = specialistName;
		this.type = type;
		this.availableTime = availableTime;
		this.availableDay = availableDay;
		this.hospital=hospital;
		this.patient=patient;
	}
	@Override
	public int compareTo(Specialist o) {
		return (this.specialistId<o.specialistId?-1:(this.specialistId>o.specialistId?1:0));
	}
	
}

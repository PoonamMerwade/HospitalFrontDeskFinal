package com.cts.model;

import java.util.List;

//import java.io.Serializable;
//import java.util.List;

//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
//import javax.xml.bind.annotation.XmlAccessType;
//import javax.xml.bind.annotation.XmlAccessorType;
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity(name ="hospital")
public class Hospital implements Comparable<Hospital>  {
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private int hospitalId ;
	
	private String hospitalName;
	
	public int bedsAvailable;
	
	@JsonManagedReference
	@OneToMany
	private List<Specialist> specialist;
	
	@JsonManagedReference
	@OneToMany
	@JoinColumn(name="patientId")
	private List<Patient> patient;
	
	public Hospital() {
		super();
	}
	
	public Hospital(int hospitalId, String hospitalName, int bedsAvailable, List<Specialist> specialist,List<Patient> patient) {
		super();
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.bedsAvailable = bedsAvailable;
		this.specialist=specialist;
		this.patient=patient;
	}

	
	@Override
	public int compareTo(Hospital o) {
		return (this.hospitalId<o.hospitalId?-1:(this.hospitalId>o.hospitalId?1:0));
	}
		
}

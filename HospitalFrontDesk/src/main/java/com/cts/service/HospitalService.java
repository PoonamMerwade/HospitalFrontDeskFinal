package com.cts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.cts.model.Hospital;
import com.cts.model.Patient;
import com.cts.repository.HospitalRepository;
import com.cts.repository.PatientRepository;
@ComponentScan


@Service
public class HospitalService{
	
	@Autowired
	HospitalRepository hospitalRepository;
		
	@Autowired
	PatientRepository patientRepository;
	
	public List<Hospital> getAll() {
		return (List<Hospital>) hospitalRepository.findAll();
	}
	
	public List<String> getAllPatient(int hospitalId) {
		return (List<String>) patientRepository.findAll(hospitalId);		
	}

	public int getBedsByHospitalName(String hospitalName) {
		Hospital hosp=hospitalRepository.findBedsByHospitalName(hospitalName);
		List<String> list1 = getAllPatient(hosp.getHospitalId()); //notdis
		for (String object : list1) {
			if(object.matches("discharged")) {
				hosp.setBedsAvailable(hosp.getBedsAvailable()+1);
			}
		}
		return hosp.getBedsAvailable();

	}
}

package com.cts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cts.model.Specialist;
import com.cts.repository.SpecialistRepository;

@Service
public class SpecialistService {
	
	@Autowired
	SpecialistRepository specialistRepository;
	
	//@Cacheable("specialist")
	public List<Specialist> getAll() {
		return (List<Specialist>)specialistRepository.findAll();
	}

	public List<Specialist> getSpecialistDetailsByType(String type) {
		return (List<Specialist>) specialistRepository.findByType(type);
	}

	public void addSpecialist(Specialist specialist) {
		specialistRepository.save(specialist);
	}

	public List<Specialist> getSpecialistDetails(String type, String availableDay, String availableTime) {
		return (List<Specialist>) specialistRepository.isSpecialistAvailable(type,availableDay,availableTime);
	}

	public List<Specialist> getSpecialistByHospitalName(String hospitalName, String type) {
		return (List<Specialist>) specialistRepository.checkSpecialistByHospitalName(hospitalName,type);
	}

}

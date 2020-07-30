package com.cts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.model.Patient;
import com.cts.repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	PatientRepository patientRepository;

	public void addPatient(Patient patient) {
		patientRepository.save(patient);		
	}

	public void deletePatient(int patientId) {
		patientRepository.deleteById(patientId);
		
	}

	public void updatePatient(Patient patient) {
		patientRepository.save(patient);
		
	}
	public Optional<Patient> getPatientById(int patientId) {
		return patientRepository.findById(patientId);
	}

	public List<Object> getAppointmentByPatientName(String patientName) {
		return patientRepository.getAppointmentByPatientName(patientName);
	}
		
}

	
	

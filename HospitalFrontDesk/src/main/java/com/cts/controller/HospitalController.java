package com.cts.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.Hospital;
import com.cts.model.Patient;
import com.cts.model.Specialist;
import com.cts.service.HospitalService;
import com.cts.service.PatientService;
import com.cts.service.SpecialistService;

@RestController
public class HospitalController {
	
	Logger logger = LoggerFactory.getLogger(HospitalController.class);

	@Autowired
	HospitalService hospitalService;
	
	@Autowired
	SpecialistService specialistService;
	
	@Autowired
	PatientService patientService;

	//controller for specialist
	@GetMapping("/specialist")
	public List<Specialist> getAll(){
		return (List<Specialist>) specialistService.getAll();		
	}
	
	@GetMapping("/specialist/{type}")
	public List<Specialist> getSpecialistDetailsByType(@PathVariable String type){
		return specialistService.getSpecialistDetailsByType(type);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/specialist/add")
	public void addSpecialist(@RequestBody Specialist specialist){
		specialistService.addSpecialist(specialist);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/specialist/{type}/{availableDay}/{availableTime}")
	public List<Specialist> getSpecialistDetails(@PathVariable("type") String type, @PathVariable("availableDay") String availableDay, @PathVariable("availableTime") String availableTime){
		logger.debug("Request: {} | {} | {} ", type,availableDay,availableTime);
		List<Specialist> list = specialistService.getSpecialistDetails(type,availableDay,availableTime);
		if (list.isEmpty()) {
            throw new RuntimeException("Failed !");
        } else {
            logger.info("Response: Successfully Executed!");
        }
        return list;
	}	

	@GetMapping("/specialist/{hospitalName}/{type}")
	public List<Specialist> getSpecialistByHospitalName(@PathVariable("hospitalName") String hospitalName, @PathVariable("type") String type){
		logger.debug("Request: {} | {} ",hospitalName, type);
		List<Specialist> specs = specialistService.getSpecialistByHospitalName(hospitalName,type);
		if (specs.isEmpty()) {
	        throw new RuntimeException("Failed Execution!");
	    } else {
	        logger.info("Response: Successfully Executed!");
	    }
	    return specs;

	}

	//controller for appointment
	@GetMapping("/getAppointment/{patientName}")
	public List<Object> getAppointment(@PathVariable("patientName") String patientName){
		return (List<Object>) patientService.getAppointmentByPatientName(patientName);
	}
	
	//controller for hospital 
	@RequestMapping("/hospital")
	public List<Hospital> getAllHospitalDetails(){
		return (List<Hospital>) hospitalService.getAll();
	}
		
	@GetMapping("/hospital/{hospitalName}")
	public int getBeds(@PathVariable String hospitalName){
		return hospitalService.getBedsByHospitalName(hospitalName);
	}
	
	//controller for patient	
	@GetMapping("/patient/{patientId}")
	public Optional<Patient> getById(@PathVariable int patientId){
		return patientService.getPatientById(patientId);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/patient/add")
	public void addPatient(@RequestBody Patient patient){
		patientService.addPatient(patient);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/patient/{patientId}")
	void deletePatientById(@PathVariable int patientId){
		patientService.deletePatient(patientId);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/patient/update/{patientId}")
	public void updatePatientById(@RequestBody Patient patient){
		patientService.updatePatient(patient);
	}
}

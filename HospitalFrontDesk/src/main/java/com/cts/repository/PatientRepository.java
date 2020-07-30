package com.cts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

@Query(value="SELECT s.specialistName, s.type, s.availableDay, s.availableTime, p.patientName FROM specialist s, patient p WHERE p.patientName=:patientName AND s.specialistId= p.specialistId",nativeQuery=true)
//	@Query(value="SELECT s.specialistName,s.type,s.availableDay,s.availableTime,p.patientName FROM patient p JOIN specialist s ON p.patientName=:patientName AND s.specialistId= p.specialistId",nativeQuery=true)
	List<Object> getAppointmentByPatientName(@Param("patientName") String patientName);
	
@Query(value="SELECT p.patientStatus from patient p where p.hospitalId=:hospitalId ",nativeQuery=true)
List<String> findAll(@Param("hospitalId") int hospitalId);
}

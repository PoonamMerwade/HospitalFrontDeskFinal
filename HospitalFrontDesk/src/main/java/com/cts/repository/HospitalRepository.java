package com.cts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.model.Hospital;
import com.cts.model.Patient;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Integer>{
	
	@Query(value="SELECT * FROM hospital where hospitalName=:hospitalName",nativeQuery=true)
	Hospital  findBedsByHospitalName(String hospitalName);
}
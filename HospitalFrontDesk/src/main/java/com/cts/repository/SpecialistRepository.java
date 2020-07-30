package com.cts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.model.Specialist;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist,Integer> {
	
	@Query
	List<Specialist> findByType(String type);

	@Query(value="SELECT * from specialist s WHERE s.type=:type AND s.availableDay=:availableDay AND s.availableTime=:availableTime", nativeQuery=true)
	List<Specialist> isSpecialistAvailable(String type, String availableDay, String availableTime);

	@Query(value="SELECT * from specialist s,hospital h where h.hospitalName=:hospitalName AND s.type=:type AND h.hospitalId=s.hospitalId",nativeQuery=true)
	List<Specialist> checkSpecialistByHospitalName(@Param("hospitalName") String hospitalName, @Param("type") String type);
		
}

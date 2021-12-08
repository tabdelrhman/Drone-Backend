package com.musala.test.drones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.musala.test.drones.entities.Drone;

public interface  DroneRepository extends JpaRepository<Drone, Long> {

	@Query(value="select DRONE_SEQ.nextval" , nativeQuery = true)
	int getDroneSeqNextValue();
	
}

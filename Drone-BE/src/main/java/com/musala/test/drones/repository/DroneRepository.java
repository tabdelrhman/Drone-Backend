package com.musala.test.drones.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.musala.test.drones.entities.Drone;
import com.musala.test.drones.entities.Drone.State;

@CrossOrigin("*")
public interface  DroneRepository extends JpaRepository<Drone, Long> {

	@Query(value="select DRONE_SEQ.nextval" , nativeQuery = true)
	int getDroneSeqNextValue();
	Set<Drone> findByState(State stateId);
	Drone findByDroneId(Long droneId);
	
}

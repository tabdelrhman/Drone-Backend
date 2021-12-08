package com.musala.test.drones.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.musala.test.drones.entities.Drone;

@Service
public interface DroneService {

	Drone addNewDrone(Drone drone);
	List<Drone> getAllDrones();

}

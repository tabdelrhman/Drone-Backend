package com.musala.test.drones.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.musala.test.drones.entities.Drone;
import com.musala.test.drones.entities.Drone.Model;
import com.musala.test.drones.service.DroneService;

@RestController
public class DroneController {

	
	@Autowired
	DroneService droneSerivce;
	
	@GetMapping("/all-drones")
	List<Drone> getAllDrones(){
		
		return droneSerivce.getAllDrones();
	}
	
	@PostMapping("/add-dronee")
	@ResponseStatus(HttpStatus.CREATED) 
	Drone registerNewDrone(@RequestBody Drone drone) {
		
		Drone savedDrone = droneSerivce.addNewDrone(drone);
		return savedDrone;
	}
	
	@GetMapping("/drone-models")
	Model[] getAllDroneModels() {
		
		Model[] values = Drone.Model.values();
		return values;
		
	}
	

}

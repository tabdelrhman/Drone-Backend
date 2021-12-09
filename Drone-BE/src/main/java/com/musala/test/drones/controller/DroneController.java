package com.musala.test.drones.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.musala.test.drones.entities.Drone;
import com.musala.test.drones.entities.Medicine;
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
	
	@PostMapping("/load/medicine/{medicineId}/to/{droneId}")
	Drone loadMedicineToDrone(@PathVariable Long medicineId, @PathVariable Long droneId) throws Exception {
		
		return droneSerivce.loadMedicineToDrone(droneId, medicineId) ;
	}
	
	@GetMapping("/loaded-medicine/{droneId}")
	Set<Medicine> getLoadedMedicine(@PathVariable Long droneId){
		return droneSerivce.getLoadedMedicine(droneId);
	}
	
	@GetMapping("/available-drones")
	Set<Drone> getAvailableDroneForLoading(){
		return droneSerivce.getAvailableDronesForLoading();
	}
	
	@GetMapping("/drone-battery/{droneId}")
	int getDroneBattery(@PathVariable Long droneId){
		return droneSerivce.getDroneBatteryLevel(droneId);
	}
	
	@PutMapping("/drone-loaded/{droneId}")
	void changeStateToLoaded(@PathVariable Long droneId){
		 droneSerivce.changeStateToLoaded(droneId);
	}
	
	@PutMapping("/drone-delivering/{droneId}")
	void changeStateToDelivering(@PathVariable Long droneId){
		 droneSerivce.changeStateToDelivering(droneId);
	}
	
	@PutMapping("/drone-delivered/{droneId}")
	void changeStateToDelivered(@PathVariable Long droneId){
		 droneSerivce.changeStateToDelivered(droneId);
	}
	
	@PutMapping("/drone-returning/{droneId}")
	void changeStateToReturning(@PathVariable Long droneId){
		 droneSerivce.changeStateToReturning(droneId);
	}
	
	@PutMapping("/drone-idle/{droneId}")
	void changeStateToIdle(@PathVariable Long droneId){
		 droneSerivce.changeStateToIdleAgain(droneId);
	}
	


}

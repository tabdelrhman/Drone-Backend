package com.musala.test.drones.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.musala.test.drones.entities.Drone;
import com.musala.test.drones.entities.Medicine;

@Service
public interface DroneService {

	Drone addNewDrone(Drone drone);
	List<Drone> getAllDrones();
	
Drone loadMedicineToDrone(Long droneId, Long medicineId) throws Exception;
	
	Set<Medicine> getLoadedMedicine(Long droneId);
	Set<Drone> getAvailableDronesForLoading();
	int getDroneBatteryLevel(long droneId);
	
	void changeStateToLoaded(Long droneId);
	void changeStateToDelivering(Long droneId);
	void changeStateToDelivered(Long droneId);
	void changeStateToReturning(Long droneId);
    void changeStateToIdleAgain(Long droneId);

}

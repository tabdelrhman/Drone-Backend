package com.musala.test.drones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.musala.test.drones.entities.Drone;
import com.musala.test.drones.repository.DroneRepository;
import com.musala.test.drones.repository.MedicineRepository;

@Component
public class DroneServiceImpl implements DroneService {

	@Autowired
	DroneRepository droneRepository;

	@Autowired
	MedicineRepository medicineRepository;

	@Override
	public List<Drone> getAllDrones() {
		return droneRepository.findAll();
	}

	@Override
	public Drone addNewDrone(Drone drone) {

		int currSequance = droneRepository.getDroneSeqNextValue();

		String serialNumber = "DRN-" + drone.getModel().toString().substring(0, 3).toUpperCase() + "-" + currSequance;
		drone.setState(Drone.State.IDLE);
		drone.setModel(Drone.Model.valueOf(drone.getModel().toString()));
		drone.setSerialNumber(serialNumber);

		Drone savedDrone = droneRepository.save(drone);
		return savedDrone;
	}

}

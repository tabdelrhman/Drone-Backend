package com.musala.test.drones.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.musala.test.drones.entities.Drone;
import com.musala.test.drones.entities.Drone.State;
import com.musala.test.drones.execption.DroneValidationException;
import com.musala.test.drones.entities.Medicine;
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

	@Override
	public Drone loadMedicineToDrone(Long droneId, Long medicineId) {

		Drone drone = droneRepository.findById(droneId).get();
		Medicine medicine = medicineRepository.findById(medicineId).get();

		doDroneChecks(drone, medicine);

		drone.setWeightLimit(drone.getWeightLimit() - medicine.getWeight());
		drone.getMedicines().add(medicine);
		drone.setState(State.LOADING);
		droneRepository.save(drone);

		return drone;
	}

	private void doDroneChecks(Drone drone, Medicine medicine) {

		if (drone.getWeightLimit() < medicine.getWeight()) {
			throw new DroneValidationException("Error: Total medicines weight should be less than 500g.",
					HttpStatus.BAD_REQUEST);
		}
		if (drone.getBatteryCapacity() <= 0) {
			throw new DroneValidationException("Error: Battery Low for this drone.", HttpStatus.BAD_REQUEST);
		}
		if (!(drone.getState().ordinal() == 0 || drone.getState().ordinal() == 1)) {
			throw new DroneValidationException("Error: Drone is not available at this moment", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public Set<Medicine> getLoadedMedicine(Long droneId) {
		return droneRepository.findById(droneId).get().getMedicines();
	}

	@Override
	public Set<Drone> getAvailableDronesForLoading() {
		State stateId = State.IDLE;
		return droneRepository.findByState(stateId);
	}

	@Override
	public int getDroneBatteryLevel(long droneId) {

		Drone drone = droneRepository.findByDroneId(droneId);
		return drone.getBatteryCapacity();
	}

	@Override
	public void changeStateToLoaded(Long droneId) {

		Drone drone = droneRepository.findById(droneId).get();
		drone.setState(State.LOADED);
		droneRepository.save(drone);

	}

	@Override
	public void changeStateToDelivering(Long droneId) {

		Drone drone = droneRepository.findById(droneId).get();
		drone.setState(State.DELIVERING);
		droneRepository.save(drone);
	}

	@Override
	public void changeStateToDelivered(Long droneId) {

		Drone drone = droneRepository.findById(droneId).get();
		drone.setState(State.DELIVERED);
		droneRepository.save(drone);
	}

	@Override
	public void changeStateToReturning(Long droneId) {

		Drone drone = droneRepository.findById(droneId).get();
		drone.setState(State.RETURNING);
		droneRepository.save(drone);
	}

	@Override
	public void changeStateToIdleAgain(Long droneId) {

		Drone drone = droneRepository.findById(droneId).get();
		drone.setState(State.IDLE);
		drone.setBatteryCapacity(drone.getBatteryCapacity() - 25);
		droneRepository.save(drone);
	}

}

package com.musala.test.drones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.musala.test.drones.entities.Medicine;
import com.musala.test.drones.repository.MedicineRepository;

@Component
public class MedicineServiceImpl implements MedicineService {

	@Autowired
	MedicineRepository medicineRepo;

	@Override
	public Medicine addNewMedicine(Medicine medicine) {
		return medicineRepo.save(medicine);
	}
}

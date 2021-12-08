package com.musala.test.drones.service;

import org.springframework.stereotype.Service;

import com.musala.test.drones.entities.Medicine;

@Service
public interface MedicineService {

	Medicine addNewMedicine(Medicine medicine);
}

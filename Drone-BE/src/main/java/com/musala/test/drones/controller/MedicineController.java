package com.musala.test.drones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.musala.test.drones.entities.Medicine;
import com.musala.test.drones.service.MedicineService;

@RestController
public class MedicineController {

	@Autowired
	MedicineService medicineService;
	
	@PostMapping("/add-new-medicine")
	@ResponseStatus(HttpStatus.CREATED) 
	Medicine addNewMedicine(@RequestBody Medicine medicine) {
	
		return medicineService.addNewMedicine(medicine);
		
	}
	
	
}

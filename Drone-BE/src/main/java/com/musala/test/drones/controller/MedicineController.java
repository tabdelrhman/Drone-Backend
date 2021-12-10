package com.musala.test.drones.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.musala.test.drones.entities.Medicine;
import com.musala.test.drones.entities.MedicinePhoto;
import com.musala.test.drones.service.MedicineService;

@RestController
@CrossOrigin("*")
public class MedicineController {

	@Autowired
	MedicineService medicineService;

	@PostMapping("/add-new-medicine")
	@ResponseStatus(HttpStatus.CREATED)
	Medicine addNewMedicine(@RequestBody Medicine medicine) {

		return medicineService.addNewMedicine(medicine);

	}

	@PostMapping("/medicine/upload")
	public Medicine addMedicinceWithPhoto(@RequestParam("imageFile") MultipartFile file,
			@RequestParam("code") String code, @RequestParam("name") String name, @RequestParam("weight") int weight)
			throws IOException {
		return medicineService.addMedicinceWithPhoto(file, code, name, weight);
	}

	@GetMapping(path = { "medicine/get/{imageName}" })
	public MedicinePhoto getImage(@PathVariable("imageName") String imageName) throws IOException {
		return medicineService.getMedicinePhoto(imageName);
	}

	@GetMapping(path = { "all-medicines" })
	public List<Medicine> getAllMedicine() throws IOException {
		return medicineService.getAllMedicines();
	}

}

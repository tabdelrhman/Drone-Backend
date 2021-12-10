package com.musala.test.drones.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.musala.test.drones.entities.Medicine;
import com.musala.test.drones.entities.MedicinePhoto;

@Service
public interface MedicineService {

	Medicine addNewMedicine(Medicine medicine);
	Medicine addMedicinceWithPhoto(MultipartFile file, String code,String name,int weight) throws IOException;
	MedicinePhoto getMedicinePhoto(String imageName);
	List<Medicine> getAllMedicines();
}

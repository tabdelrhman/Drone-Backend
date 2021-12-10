package com.musala.test.drones.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.musala.test.drones.entities.MedicinePhoto;

@CrossOrigin("*")
public interface MedicinePhotoRepository extends JpaRepository<MedicinePhoto, Long>{

	Optional<MedicinePhoto> findByName(String name);
}

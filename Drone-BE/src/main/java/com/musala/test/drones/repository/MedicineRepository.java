package com.musala.test.drones.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.test.drones.entities.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

}

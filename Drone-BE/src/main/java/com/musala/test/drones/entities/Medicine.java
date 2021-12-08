package com.musala.test.drones.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Medicine {

	@Id
	@GeneratedValue
	@JsonIgnore
	private Long medicineId;
	
	private String name;
	
	private String code;
	
	private double weight;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "medicines")
	@JsonIgnore
	private Set<Drone> drone;
	
	public Medicine() {
		
	}

	public Medicine(Long medicineId, String name, String code, double weight, Set<Drone> drone) {
		super();
		this.medicineId = medicineId;
		this.name = name;
		this.code = code;
		this.weight = weight;
		this.drone = drone;
	}

	public Long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Set<Drone> getDrone() {
		return drone;
	}

	public void setDrone(Set<Drone> drone) {
		this.drone = drone;
	}	
	
}

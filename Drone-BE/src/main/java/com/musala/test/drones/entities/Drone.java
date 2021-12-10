package com.musala.test.drones.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;


@Entity
public class Drone {

	 public enum Model {Lightweight, Middleweight, Cruiserweight, Heavyweight};
	 public enum State {IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING};

	 @Id @GeneratedValue
	 private Long droneId;
	 
	 @NonNull
	 @Size(min = 5,message = "Serial number should be more than 5 characters!")
	 private String serialNumber;
	 
	 @Max(value = 100 , message = "Battery capacity should be below 100 percent!")
	 private int batteryCapacity;
	 
	 @Max(value = 500 , message = "Weight limit should be 500g or less!")
	 private double weightLimit;
	 
	 private Model model;
	 
	 private State state;

	 @ManyToMany(fetch = FetchType.LAZY)
	 @JoinTable(name = "drone_medicine", joinColumns = { @JoinColumn(name = "droneId") }, inverseJoinColumns = {
	 @JoinColumn(name = "medicineId") })
	 private Set<Medicine> medicines;
	 
	 
	public Drone() {
		 
	 }


	public Drone(Long droneId, String serialNumber, int batteryCapacity, double weightLimit, Model model, State state,
			Set<Medicine> medicines) {
		super();
		this.droneId = droneId;
		this.serialNumber = serialNumber;
		this.batteryCapacity = batteryCapacity;
		this.weightLimit = weightLimit;
		this.model = model;
		this.state = state;
		this.medicines = medicines;
	}


	public Long getDroneId() {
		return droneId;
	}


	public void setDroneId(Long droneId) {
		this.droneId = droneId;
	}


	public String getSerialNumber() {
		return serialNumber;
	}


	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}


	public int getBatteryCapacity() {
		return batteryCapacity;
	}


	public void setBatteryCapacity(int batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}


	public double getWeightLimit() {
		return weightLimit;
	}


	public void setWeightLimit(double weightLimit) {
		this.weightLimit = weightLimit;
	}


	public Model getModel() {
		return model;
	}


	public void setModel(Model model) {
		this.model = model;
	}


	public State getState() {
		return state;
	}


	public void setState(State state) {
		this.state = state;
	}


	public Set<Medicine> getMedicines() {
		return medicines;
	}


	public void setMedicines(Set<Medicine> medicines) {
		this.medicines = medicines;
	}

	
		
}

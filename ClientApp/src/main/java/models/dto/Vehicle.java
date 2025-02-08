package models.dto;

import java.io.Serializable;

public class Vehicle implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String vehicleType;
	public Vehicle(String type) {
		this.vehicleType=type;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	
	
}

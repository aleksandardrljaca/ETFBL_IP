package dao;

import java.sql.SQLException;

import models.dto.Vehicle;

public interface VehicleDAO {
	String getFreeVehicle(String type) throws SQLException;
}	

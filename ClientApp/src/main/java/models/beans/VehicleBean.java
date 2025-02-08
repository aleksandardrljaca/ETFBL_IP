package models.beans;

import java.io.Serializable;
import java.sql.SQLException;

import dao.daoImpl.VehicleDAOImpl;
import models.dto.Vehicle;

public class VehicleBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getFreeVehicle(String type) {
		String v=null;
		try{
			v= new VehicleDAOImpl().getFreeVehicle(type);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return v;
	}

}

package models.beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;

import dao.daoImpl.LocationDAOImpl;
import models.dto.Location;
import utils.GeoCoder;

public class LocationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Location insert(String name) {
		try {
			return GeoCoder.getCoordinates(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}

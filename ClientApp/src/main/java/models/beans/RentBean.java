package models.beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import dao.daoImpl.RentDAOImpl;
import models.dto.Location;
import models.dto.Rent;

public class RentBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean insert(Integer clientId,Integer startLocId,Integer endLocId,String vehicleId,Double price,String paymentInfo) {
		try {
			return new RentDAOImpl().insert(clientId,startLocId,endLocId,vehicleId,price,paymentInfo);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public List<Rent> getClientsRents(Integer clientId){
		List<Rent> rents=null;
		try {
			rents=new RentDAOImpl().getClientsRents(clientId);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rents;
	}

}

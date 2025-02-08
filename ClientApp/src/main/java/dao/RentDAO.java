package dao;

import java.sql.SQLException;
import java.util.List;

import models.dto.Location;
import models.dto.Rent;

public interface RentDAO {
	List<Rent> getClientsRents(Integer clientId) throws SQLException;
	boolean insert(Integer clientId,Integer startLocId,Integer endLocId,String vehicleId,Double price,String paymentInfo) throws SQLException;
}

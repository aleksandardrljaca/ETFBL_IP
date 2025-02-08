package dao;
import java.sql.SQLException;

import models.dto.Client;
public interface ClientDAO {
	
	Client loadByUsername(String username) throws SQLException;
	boolean register(String firstname,String lastname,String username,String password,String idCard,String drivingLicense,String phone,String email) throws SQLException;
	Client update(Integer id,String firstname,String lastname,String username,String password,String idCard,String drivingLicense,String phone,String email) throws SQLException;
	boolean delete(Integer id) throws SQLException;
}

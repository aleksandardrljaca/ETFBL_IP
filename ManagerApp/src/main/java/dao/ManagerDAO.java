package dao;

import java.sql.SQLException;

import beans.ManagerBean;

public interface ManagerDAO {
	ManagerBean loadByUsername(String username) throws SQLException;
}	

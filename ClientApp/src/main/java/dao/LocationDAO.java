package dao;

import java.sql.SQLException;

import models.dto.Location;

public interface LocationDAO {
	Location insert(String name,Double lat,Double lo) throws SQLException;
}

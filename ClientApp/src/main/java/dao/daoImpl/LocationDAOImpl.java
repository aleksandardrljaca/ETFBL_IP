package dao.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import dao.LocationDAO;
import dao.pool.ConnectionPool;
import models.dto.Location;
import models.dto.Vehicle;
import utils.DBUtil;

public class LocationDAOImpl implements LocationDAO{
	private ConnectionPool connectionPool;
	private static final String INSERT="insert into location(name,latitude,longitude) values(?,?,?)";
	public LocationDAOImpl() {
		this.connectionPool = ConnectionPool.getInstance();
	}
	

	@Override
	public Location insert(String name, Double lat, Double lo) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		Object values[] = { name,lat,lo  };
		ResultSet rs=null;
		Location location=new Location(name,lat,lo);
		try {
			connection = connectionPool.checkOut();
			statement = DBUtil.prepareStatement(connection, INSERT, true, values);
			statement.executeUpdate();
			rs=statement.getGeneratedKeys();
			if(rs.next())
				location.setId(rs.getInt(1));
			
		} finally {
			connectionPool.checkIn(connection);
			DBUtil.close(statement);
		}
		return location;
	}
	
}

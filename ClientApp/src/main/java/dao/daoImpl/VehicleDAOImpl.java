package dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.VehicleDAO;
import dao.pool.ConnectionPool;
import models.dto.Client;
import models.dto.Vehicle;
import utils.DBUtil;

public class VehicleDAOImpl implements VehicleDAO{
	private static final String GET_VEHICLE="SELECT v.id\r\n"
			+ "FROM vehicle v\r\n"
			+ "LEFT JOIN rent r ON r.vehicle_id = v.id \r\n"
			+ "  AND r.date = CURDATE()\r\n"
			+ "  AND r.start_time <= CURTIME()\r\n"
			+ "  AND r.end_time > CURTIME()\r\n"
			+ "WHERE v.vehicle_type = ?\r\n"
			+ "  AND r.id IS NULL limit 1";
	private ConnectionPool connectionPool;
	
	public VehicleDAOImpl() {
		this.connectionPool = ConnectionPool.getInstance();
	}
	
	@Override
	public String getFreeVehicle(String type) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		Object values[] = { type };
		String vehicle = null;
		try {
			connection = connectionPool.checkOut();
			statement = DBUtil.prepareStatement(connection, GET_VEHICLE, false, values);
			rs = statement.executeQuery();
			if (rs.next()) {
				vehicle=rs.getString(1);		
			}
		} finally {
			connectionPool.checkIn(connection);
			DBUtil.close(statement, rs);
		}
		return vehicle;
	}

}

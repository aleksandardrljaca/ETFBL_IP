package dao.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dao.RentDAO;
import dao.pool.ConnectionPool;
import models.dto.Location;
import models.dto.Rent;
import models.dto.Vehicle;
import utils.DBUtil;

public class RentDAOImpl implements RentDAO {
	private static final String GET_RENTS="SELECT \r\n"
			+ "    r.date,\r\n"
			+ "    l1.name AS 'start location',\r\n"
			+ "    l2.name AS 'end location',\r\n"
			+ "    r.price,\r\n"
			+ "    r.vehicle_id,\r\n"
			+ "    r.payment_info\r\n"
			+ "FROM \r\n"
			+ "    rent r\r\n"
			+ "INNER JOIN location l1 ON r.start_location = l1.id\r\n"
			+ "INNER JOIN location l2 ON r.end_location = l2.id\r\n"
			+ "WHERE \r\n"
			+ "    r.client_id = ?";
	private static final String INSERT="insert into rent(client_id,date,start_time,end_time,start_location,end_location,price,vehicle_id,payment_info) values (?,?,?,?,?,?,?,?,?)";
	private ConnectionPool connectionPool;

	public RentDAOImpl() {
		this.connectionPool = ConnectionPool.getInstance();
	}
	@Override
	public boolean insert(Integer clientId,Integer startLocId,Integer endLocId,String vehicleId,Double price,String paymentInfo) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		LocalTime currentTime=LocalTime.now();
		LocalTime endTime=LocalTime.of(currentTime.getHour()+1, currentTime.getMinute());
		Object values[] = { clientId, LocalDate.now(), currentTime,endTime, startLocId,endLocId,price,vehicleId,paymentInfo  };
		boolean isCreated=false;
		try {
			connection = connectionPool.checkOut();
			statement = DBUtil.prepareStatement(connection, INSERT, false, values);
			isCreated=statement.executeUpdate()==1;
			
		} finally {
			connectionPool.checkIn(connection);
			DBUtil.close(statement);
		}
		return isCreated;
	}
	@Override
	public List<Rent> getClientsRents(Integer clientId) throws SQLException{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Object values[] = { clientId };
		List<Rent> rents=new ArrayList<>();
		try {
			connection = connectionPool.checkOut();
			statement = DBUtil.prepareStatement(connection, GET_RENTS, false, values);
			rs = statement.executeQuery();
			while(rs.next()) {
				rents.add(new Rent(Date.valueOf(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5),rs.getString(6)));
			}
		} finally {
			connectionPool.checkIn(connection);
			DBUtil.close(statement, rs);
		}
		return rents;
	}
	

}

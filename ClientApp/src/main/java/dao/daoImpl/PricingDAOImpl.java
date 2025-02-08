package dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.PricingDAO;
import dao.pool.ConnectionPool;

import utils.DBUtil;

public class PricingDAOImpl implements PricingDAO {
	private ConnectionPool connectionPool;
	private static final String GET_PRICE="SELECT price\r\n"
			+ "FROM pricing\r\n"
			+ "WHERE ? BETWEEN min_distance AND max_distance";
	public PricingDAOImpl() {
		this.connectionPool = ConnectionPool.getInstance();
	}
	@Override
	public Double getPrice(Integer distance) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		Object values[] = { distance  };
		ResultSet rs=null;
		Double value=null;
		try {
			connection = connectionPool.checkOut();
			statement = DBUtil.prepareStatement(connection, GET_PRICE, false, values);
			rs=statement.executeQuery();
			if(rs.next())
				value=Double.valueOf(rs.getDouble(1));
			
		} finally {
			connectionPool.checkIn(connection);
			DBUtil.close(statement);
		}
		return value;
	}

}

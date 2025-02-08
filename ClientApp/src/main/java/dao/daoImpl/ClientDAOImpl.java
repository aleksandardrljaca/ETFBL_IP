package dao.daoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ClientDAO;
import dao.pool.ConnectionPool;
import models.dto.Client;
import utils.DBUtil;
public class ClientDAOImpl implements ClientDAO{
	
	private static final String LOAD_BY_USERNAME="select * from client c where c.username=?";
	private static final String INSERT="insert into client(firstname,lastname,username,password,id_card,driving_license,phone,email,image_id,is_blocked) values(?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE="update client set firstname=?,lastname=?,username=?,password=?,id_card=?,driving_license=?,phone=?,email=? where id=?";
	private static final String DELETE="update client set is_blocked=? where id=?";
	private ConnectionPool connectionPool;

	public ClientDAOImpl() {
		this.connectionPool = ConnectionPool.getInstance();
	}
	@Override
	public Client loadByUsername(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Object values[] = { username };
		Client client = null;
		try {
			connection = connectionPool.checkOut();
			statement = DBUtil.prepareStatement(connection, LOAD_BY_USERNAME, false, values);
			rs = statement.executeQuery();
			if (rs.next()) {
				client=new Client(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9),rs.getInt(10),rs.getBoolean(11));			}
		} finally {
			connectionPool.checkIn(connection);
			DBUtil.close(statement, rs);
		}
		return client;
	}
	@Override
	public boolean register(String firstname, String lastname, String username, String password, String idCard,
			String drivingLicense, String phone, String email) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		Object values[] = { firstname,lastname,username,password,idCard,drivingLicense,phone,email,null,0 };
		boolean registered=false;
		try {
			connection = connectionPool.checkOut();
			statement = DBUtil.prepareStatement(connection, INSERT, false, values);
			registered=statement.executeUpdate()==1;
			
		} finally {
			connectionPool.checkIn(connection);
			DBUtil.close(statement);
		}
		return registered;
		
	}
	@Override
	public Client update(Integer id, String firstname, String lastname, String username, String password, String idCard,
			String drivingLicense, String phone, String email) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		Object values[] = { firstname,lastname,username,password,idCard,drivingLicense,phone,email,id };
		Client client=new Client(id,firstname,lastname,drivingLicense,idCard,email,phone,username,password);
		boolean isUpdated=false;
		try {
			connection = connectionPool.checkOut();
			statement = DBUtil.prepareStatement(connection, UPDATE, false, values);
			isUpdated=statement.executeUpdate()==1;
		} finally {
			connectionPool.checkIn(connection);
			DBUtil.close(statement);
		}
		System.out.println("AZURIRAN "+isUpdated);
		return isUpdated?client:null;
	}
	@Override
	public boolean delete(Integer id) throws SQLException {
		Connection connection=null;
		PreparedStatement ps=null;
		Object values[] = { 1,id};
		Boolean retVal=false;
		try {
			connection=connectionPool.checkOut();
			ps=DBUtil.prepareStatement(connection, DELETE, false, values);
			retVal=ps.executeUpdate()==1;
		}finally {
			DBUtil.close(ps);
		}
		return retVal;
	}
	
	
}

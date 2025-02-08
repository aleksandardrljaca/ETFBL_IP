package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.ManagerBean;
import dao.ManagerDAO;
import dao.pool.ConnectionPool;
import utils.DBUtil;

public class ManagerDAOImpl implements ManagerDAO{
	
	private ConnectionPool connectionPool;
	private static final String LOAD_BY_USERNAME="select username,password from employee where username=? and role='MANAGER'";
	public ManagerDAOImpl() {
		this.connectionPool=ConnectionPool.getInstance();
	}

	@Override
	public ManagerBean loadByUsername(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Object values[] = { username };
		ManagerBean manager = null;
		try {
			connection = connectionPool.checkOut();
			statement = DBUtil.prepareStatement(connection, LOAD_BY_USERNAME, false, values);
			rs = statement.executeQuery();
			if (rs.next()) 
				manager=new ManagerBean(rs.getString(1),rs.getString(2));			
		} finally {
			connectionPool.checkIn(connection);
			DBUtil.close(statement, rs);
		}
		return manager;
	}

}

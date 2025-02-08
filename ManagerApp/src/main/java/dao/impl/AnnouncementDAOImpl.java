package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.AnnouncementBean;
import beans.ManagerBean;
import dao.AnnouncementDAO;
import dao.pool.ConnectionPool;
import utils.DBUtil;

public class AnnouncementDAOImpl implements AnnouncementDAO {
	private ConnectionPool connectionPool;
	private static final String GET_ALL="select * from announcement";
	private static final String INSERT="insert into announcement(title,content) values(?,?)";
	public AnnouncementDAOImpl() {
		this.connectionPool=ConnectionPool.getInstance();
	}

	@Override
	public List<AnnouncementBean> getAll() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Object values[] = { };
		List<AnnouncementBean> announcements=new ArrayList<>();
		try {
			connection = connectionPool.checkOut();
			statement = DBUtil.prepareStatement(connection, GET_ALL, false, values);
			rs = statement.executeQuery();
			while(rs.next()) 
				announcements.add(new AnnouncementBean(rs.getString(2),rs.getString(3)));			
		} finally {
			connectionPool.checkIn(connection);
			DBUtil.close(statement, rs);
		}
		return announcements;
	}

	@Override
	public boolean insert(AnnouncementBean announcement) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		boolean isInserted=false;
		Object values[] = { announcement.getTitle(),announcement.getContent() };
		
		try {
			connection = connectionPool.checkOut();
			statement = DBUtil.prepareStatement(connection, INSERT, false, values);
			isInserted= statement.executeUpdate()==1;			
		} finally {
			connectionPool.checkIn(connection);
			DBUtil.close(statement);
		}
		return isInserted;
	}

}

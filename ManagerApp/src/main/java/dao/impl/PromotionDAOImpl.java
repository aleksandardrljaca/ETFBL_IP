package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.AnnouncementBean;
import beans.PromotionBean;
import dao.PromotionDAO;
import dao.pool.ConnectionPool;
import utils.DBUtil;

public class PromotionDAOImpl implements PromotionDAO {
	private ConnectionPool connectionPool;
	private static final String GET_ALL="select * from promotion";
	private static final String INSERT="insert into promotion(title,description,valid_until) values(?,?,?)";

	public PromotionDAOImpl() {
		this.connectionPool=ConnectionPool.getInstance();
	}

	@Override
	public List<PromotionBean> getAll() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Object values[] = { };
		List<PromotionBean> promotions=new ArrayList<>();
		try {
			connection = connectionPool.checkOut();
			statement = DBUtil.prepareStatement(connection, GET_ALL, false, values);
			rs = statement.executeQuery();
			while(rs.next()) 
				promotions.add(new PromotionBean(rs.getString(2),rs.getString(3),((Date)rs.getDate(4)).toLocalDate()));			
		} finally {
			connectionPool.checkIn(connection);
			DBUtil.close(statement, rs);
		}
		return promotions;
	}

	@Override
	public boolean insert(PromotionBean promotion) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		boolean isInserted=false;
		Object values[] = { promotion.getTitle(),promotion.getDescription(),promotion.getDate()};
		
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

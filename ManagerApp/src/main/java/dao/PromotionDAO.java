package dao;

import java.sql.SQLException;
import java.util.List;

import beans.PromotionBean;

public interface PromotionDAO {
	List<PromotionBean> getAll() throws SQLException;
	boolean insert(PromotionBean promotion) throws SQLException;
}

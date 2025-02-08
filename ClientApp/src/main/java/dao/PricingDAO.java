package dao;

import java.sql.SQLException;

public interface PricingDAO {
	Double getPrice(Integer distance) throws SQLException;
}

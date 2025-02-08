package models.beans;

import java.io.Serializable;
import java.sql.SQLException;

import dao.daoImpl.PricingDAOImpl;

public class PricingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Double getPrice(Integer distance) {
		Double price=0.0;
		try {
			price=new PricingDAOImpl().getPrice(distance);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return price;
	}
	
}

package services;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.PromotionBean;
import dao.impl.PromotionDAOImpl;

public class PromotionService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<PromotionBean> promotions;
	
	public PromotionService() {
		try {
			this.promotions=new PromotionDAOImpl().getAll();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public List<PromotionBean> getPromotions(){
		if(this.promotions!=null)
			return this.promotions;
		return new ArrayList<>();
	}
	public boolean insert(PromotionBean promotion) {
		boolean isInserted=false;
		try {
			isInserted=new PromotionDAOImpl().insert(promotion);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(isInserted)
			this.promotions.add(promotion);
		return isInserted;
	}
}

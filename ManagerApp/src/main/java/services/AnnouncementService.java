package services;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import beans.AnnouncementBean;

import dao.impl.AnnouncementDAOImpl;


public class AnnouncementService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<AnnouncementBean> announcements;
	
	public AnnouncementService() {
		try {
			this.announcements=new AnnouncementDAOImpl().getAll();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<AnnouncementBean> getAnnouncements(){
		if(this.announcements!=null)
			return this.announcements;
		return new ArrayList<>();
	}
	public boolean insert(AnnouncementBean announcement) {
		boolean isInserted=false;
		try {
			isInserted=new AnnouncementDAOImpl().insert(announcement);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(isInserted)
			this.announcements.add(announcement);
		return isInserted;
	}
	
	
}

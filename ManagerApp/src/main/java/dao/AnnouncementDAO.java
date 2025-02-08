package dao;

import java.sql.SQLException;
import java.util.List;

import beans.AnnouncementBean;

public interface AnnouncementDAO {
	List<AnnouncementBean> getAll() throws SQLException;
	boolean insert(AnnouncementBean announcement) throws SQLException;
}

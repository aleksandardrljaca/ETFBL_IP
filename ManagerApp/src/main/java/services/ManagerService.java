package services;

import java.io.Serializable;
import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import beans.ManagerBean;
import dao.impl.ManagerDAOImpl;

public class ManagerService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean loggedIn = false;

	public ManagerService() {
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public boolean login(String username, String password) {
		ManagerBean admin =null;
		try {
			 admin=new ManagerDAOImpl().loadByUsername(username);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if (admin == null) {
			loggedIn = false;
			return false;
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		if (encoder.matches(password, admin.getPassword())) {
			loggedIn = true;
			return true;
		}
		else {
			loggedIn = false;
			return false;
		}
	}
	
	public void logout() {
		loggedIn = false;
	}

}

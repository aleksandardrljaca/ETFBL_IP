package models.beans;

import java.io.Serializable;
import java.sql.SQLException;

import dao.daoImpl.ClientDAOImpl;
import models.dto.Client;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class ClientBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Client client;
	
	public Client login(String username,String password) {
		Client client=null;
		try {
			client=new ClientDAOImpl().loadByUsername(username);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(client!=null && !client.getIsBlocked()) {
			BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
			if(passwordEncoder.matches(password, client.getPassword())) {
				System.out.println("KLIJENT IS NULL:"+(client==null));
				this.client=client;
				return client;
			}
		}
		return null;
	}
	public boolean register(String firstname, String lastname, String username, String password, String idCard,
			String drivingLicense, String phone, String email) {
		try {
			ClientDAOImpl clientDAO=new ClientDAOImpl();
			// check if username is already taken
			if(clientDAO.loadByUsername(username)!=null)
				return false;
			BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
			return clientDAO.register(firstname, lastname, username, passwordEncoder.encode(password),idCard, drivingLicense, phone, email);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public Client update(String firstname, String lastname, String username, String password, String idCard,
			String drivingLicense, String phone, String email) {
		try {
			return new ClientDAOImpl().update(client.getId(), firstname, lastname, username, password, idCard, drivingLicense, phone, email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean deactivate() {
		boolean isDeactivated=false;
		try {
			isDeactivated=new ClientDAOImpl().delete(client.getId());
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return isDeactivated;
	}
	public Client getLoggedClient() {
		return client;
	}
	public void setLoggedClient(Client client) {
		this.client=client;
	}

}

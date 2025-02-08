package models.dto;

import java.io.Serializable;

public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String idCard;
	private String drivingLicense;
	private String phone;
	private String email;
	private Integer imageId;
	private Boolean isBlocked;
	public Client() {
		super();
	}
	public Client(Integer id,String firstname, String lastname, String drivingLicense,
			String idCard, String email, String phone,String username, String password,Integer imageId,Boolean isBlocked) {
		super();
		this.id=id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.drivingLicense = drivingLicense;
		this.idCard = idCard;
		this.email = email;
		this.phone = phone;
		this.isBlocked = isBlocked;
		this.imageId = imageId;
	}
	public Client(Integer id,String firstname, String lastname, String drivingLicense,
			String idCard, String email, String phone,String username, String password) {
		super();
		this.id=id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.drivingLicense = drivingLicense;
		this.idCard = idCard;
		this.email = email;
		this.phone = phone;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id=id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getDrivingLicense() {
		return drivingLicense;
	}
	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Boolean getIsBlocked() {
		return isBlocked;
	}
	public void setIsBlocked(Boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
	public Integer getImageId() {
		return imageId;
	}
	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}
	

}

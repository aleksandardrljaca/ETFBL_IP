package models.dto;

import java.io.Serializable;
import java.sql.Date;

public class Rent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date date;
	private String startLocation;
	private String endLocation;
	private Double price;
	private String vehicleId;
	private String paymentInfo;
	public Rent(Date date, String startLocation, String endLocation, Double price, String vehicleId,
			String paymentInfo) {
		super();
		this.date = date;
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.price = price;
		this.vehicleId = vehicleId;
		this.paymentInfo = paymentInfo;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStartLocation() {
		return startLocation;
	}
	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}
	public String getEndLocation() {
		return endLocation;
	}
	public void setEndLocation(String endLocation) {
		this.endLocation = endLocation;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getPaymentInfo() {
		return paymentInfo;
	}
	public void setPaymentInfo(String paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
	@Override
	public String toString() {
		return "Rent [date=" + date + ", startLocation=" + startLocation + ", endLocation=" + endLocation + ", price="
				+ price + ", vehicleId=" + vehicleId + ", paymentInfo=" + paymentInfo + "]";
	}
	
}

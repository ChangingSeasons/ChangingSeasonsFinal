package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	private int orderID, customerID;
	private Date dateOfOrder, dateOfShipping;
	private String orderStatus, shippingAddress;
	private float total_price, tax;
	private boolean status;
	
	public Order(){
		this.orderID = 0;
		this.customerID = 0;
		this.dateOfOrder =  new Date(Calendar.getInstance().getTime().getTime());
		this.dateOfShipping = new Date(Calendar.getInstance().getTime().getTime());
		this.orderStatus = "";
		this.shippingAddress = "";
		this.total_price = 0;
		this.tax = 8;
		this.status = true;
	}
	
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public Date getDateOfOrder() {
		return dateOfOrder;
	}
	public void setDateOfOrder(Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}
	public Date getDateOfShipping() {
		return dateOfShipping;
	}
	public void setDateOfShipping(Date dateOfShipping) {
		this.dateOfShipping = dateOfShipping;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public float getTotal_price() {
		return total_price;
	}
	public void setTotal_price(float total_price) {
		this.total_price = total_price;
	}
	public float getTax() {
		return tax;
	}
	public void setTax(float tax) {
		this.tax = tax;
	}
	
	
	
}

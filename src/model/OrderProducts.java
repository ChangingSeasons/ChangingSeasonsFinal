package model;

import java.io.Serializable;
import java.util.List;

public class OrderProducts implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String color, size;
	private String name;
	
	

	private int quantity, productID, orderID, OrderProductID;

	public OrderProducts(){
		this.color = "";
		this.size = "";
		this.quantity = 0;
		this.productID = 0;
		this.orderID = 0;
		this.OrderProductID = 0;
		this.name = "";
	}


	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getOrderProductID() {
		return OrderProductID;
	}

	public void setOrderProductID(int orderProductID) {
		OrderProductID = orderProductID;
	}


}

package model;

import static model.ProductDAO.*;
import static model.ConnectDB.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class ShoppingCartDAO {

	public static ShoppingCart cartDetails(int customerID){

		ShoppingCart sc = null;

		int cartID = getCartID(customerID);
		int noOfProducts = noOfproductsIncart(customerID);

		//HashMap<Product, Integer> hm = new HashMap<Product, Integer>();
		
		List<Product> cartProducts = new ArrayList<Product>();

		try{
			Connect();
			String q0 = "SELECT c.cartProductID, c.productID,p.productName,p.productDesc,p.sellerID,p.price,p.imagePath,p.shippingCost,c.size,c.color,p.imageName,p.type,p.status,c.quantity FROM CartProducts c JOIN Product p ON "
					+ "c.productID=p.productID WHERE cartID="+cartID;

			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q0);

			while(rs.next()){
				Product p = new Product();
				p.setCartProductID(rs.getInt("cartProductID"));
				p.setProductID(rs.getInt("productID"));
				p.setProductName(rs.getString("productName"));
				p.setProductDesc(rs.getString("productDesc"));
				p.setSellerID(rs.getInt("sellerID"));
				p.setPrice(rs.getFloat("price"));
				p.setImagePath(rs.getString("imagePath"));
				p.setShippingCost(rs.getFloat("shippingCost"));
				p.setSize(rs.getString("size"));
				p.setColor(rs.getString("color"));
				p.setImageName(rs.getString("imageName"));
				p.setType(rs.getString("type"));
				p.setStatus(rs.getBoolean("status"));
				p.setQuantity(rs.getInt("quantity"));
				p.setCompanyName(getCompanyNameForProduct(rs.getInt("sellerID")));
				Connect();
				cartProducts.add(p);
			}
			st.close();
			rs.close();


			q0 = "SELECT * FROM ShoppingCart WHERE cartID="+cartID;
			st = cn.createStatement();
			rs = st.executeQuery(q0);

			sc = new ShoppingCart();

			while(rs.next()){
				sc.setCartID(cartID);
				sc.setCustomerID(customerID);
				sc.setDateAdded(rs.getDate("dateAdded"));
				sc.setTotalPrice(rs.getFloat("totalPrice"));
				sc.setCartProducts(cartProducts);
				sc.setNoOfProducts(noOfProducts);
			}
			rs.close();
			st.close();

		}catch(SQLException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();
		return sc;
	}

	private static int getLastID(){
		Connect();
		int ID = -1;
		try{
			String q0 = "SELECT cartID FROM ShoppingCart";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q0);

			if(rs.next()){
				rs.last(); // Get ID of last Cart
				ID = rs.getInt("cartID");
				ID++;
			}
			else
				ID=1; // Empty Table, so start with ID 1

			rs.close();
			st.close();
		}catch(SQLException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();
		return ID;
	}

	public static int getCartID(int customerID){
		int ID = -1;
		Connect();
		try{
			String q0 = "SELECT cartID FROM ShoppingCart WHERE customerID="+customerID;
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q0);

			if(rs.next())   // User already has a cart
				ID = rs.getInt("cartID");
			else // No cart associated with the user. Generate a new cartID for the user
				ID = getLastID();

			rs.close();
			st.close();

		}catch(SQLException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		DB_close();
		return ID;
	}

	public static boolean createCart(int customerID){ // Called upon Successful customer registration

		Connect();
		try{
			Calendar calendar = Calendar.getInstance();
			Date startDate = new Date(calendar.getTime().getTime());

			String q = "INSERT into ShoppingCart (cartID, dateAdded, customerID, totalPrice) values (?, ?, ?, ?)";
			PreparedStatement ps = cn.prepareStatement(q);

			ps.setInt(1, getCartID(customerID));
			Connect();
			ps.setDate(2, startDate);
			ps.setInt(3, customerID);
			ps.setFloat(4, 0); // Initially Total price of Cart would be 0

			ps.executeUpdate();

			ps.close();
		}catch(SQLException se){
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
		DB_close();
		return true;
	}

	public static float updateTotalPrice(int customerID){
		float price = 0.0f;

		price = totalPrice(customerID);
		System.out.println("Total price before QWuery: "+price);
		
		if(price>0){ // Its 0 by default, hence a check
			try{
				Connect();
				String q0 = "UPDATE ShoppingCart SET totalPrice="+price+" WHERE customerID="+customerID;
				Statement st = cn.createStatement();
				st.executeUpdate(q0);

				st.close();
			}catch(SQLException e){
				System.err.println(e.getMessage());
				e.printStackTrace();
			}		
		}
		
		DB_close();
		
		System.out.println("Total Price after Query:"+price);
		return price;
	}
	
	public static float totalWithtax(int customerID){
		float price = 0.0f;

		price = totalPrice(customerID);
		
		if(price > 0)
			return (price * 1.08f);
		return 0;
		
	}

	public static int noOfproductsIncart(int userID){
		int count = 0;

		int cartID = getCartID(userID);

		try{
			Connect();
			String q0 = "SELECT productID FROM cartProducts WHERE cartID="+cartID;
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q0);

			if(rs.next()){
				rs.last();
				count = rs.getRow(); // Total Number of products in Cart
			}
			else
				count = 0; // Cart is empty

			rs.close();
			st.close();

		}catch(SQLException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();
		System.out.println("No of product:"+count);
		return count;
	}

	public static boolean removeProduct(int customerID, int productID, String size, String color){
		int cartID = getCartID(customerID);

		try{
			Connect();
			String q = "DELETE FROM CartProducts WHERE cartID="+cartID+" AND productID="+productID+
					" AND size='"+size+"' AND color='"+color+"'";
			Statement st = cn.createStatement();
			st.executeUpdate(q);

			st.close();

		}catch(SQLException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();
		return true;
	}

	public static float totalPrice(int customerID){
		float amount = 0.0f;

		if(noOfproductsIncart(customerID)==0) // Empty Cart, Total price --> 0
			return 0;

		List<Integer> productID = new ArrayList<Integer>();
		List<Integer> quantity = new ArrayList<Integer>();
		List<Float> price = new ArrayList<Float>();

		try{
			int cartID = getCartID(customerID);
			Connect();
			String q0 = "SELECT productID, quantity FROM CartProducts WHERE cartID="+cartID;
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q0);


			while(rs.next()){
				productID.add(rs.getInt("productID"));
				quantity.add(rs.getInt("quantity"));
			}

			st.close();
			rs.close();

			Iterator<Integer> itr = productID.iterator();
			while(itr.hasNext()){
				int temp = (int)itr.next();
				q0 = "SELECT price FROM Product WHERE productID="+temp;
				st = cn.createStatement();
				rs = st.executeQuery(q0);
				while(rs.next()){
					price.add(rs.getFloat("price"));
				}
			}
			rs.close();
			st.close();

			Iterator<Integer> itr2 = quantity.iterator();
			Iterator<Float> itr3 = price.iterator();

			while(itr2.hasNext()){
				int temp_quant = (int)itr2.next();
				float temp_cost = (float)itr3.next();

				amount += temp_quant * temp_cost;
			}

		}catch(SQLException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		DB_close();
		System.out.println("AMount"+amount);
		return amount;
	}
	
	public static boolean initShoppingCart(int customerID){ // This is called after Order is Placed
	
		try{
			int cartID = getCartID(customerID);
			Connect();
			String q0 = "DELETE FROM CartProducts WHERE cartID="+cartID;
			Statement st = cn.createStatement();
			st.executeUpdate(q0);
			st.close();		
			
			q0 = "UPDATE ShoppingCart SET totalPrice=0 WHERE customerID="+customerID;
			st = cn.createStatement();
			st.executeUpdate(q0);

			st.close();
			
		}catch(SQLException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		DB_close();
		return true;
	}

}

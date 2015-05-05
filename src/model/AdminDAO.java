package model;

import static model.ConnectDB.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdminDAO {

	public static boolean deleteUser(int userID){
		Connect();

		try{
			String q = "UPDATE User SET status=0 WHERE id="+userID;
			Statement st = cn.createStatement();
			st.executeUpdate(q);

			st.close();
		}catch(SQLException se){
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
		DB_close();
		return true;
	}

	public static boolean authorizeSeller(boolean status, int sellerID){
		Connect();

		try{
			String q = "UPDATE Seller SET authorized="+status+" WHERE id="+sellerID;
			Statement st = cn.createStatement();
			st.executeUpdate(q);

			st.close();
		}catch(SQLException se){
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
		DB_close();
		return true;
	}

	public static int noOfusers(){

		Connect();

		int countRows = 0;
		try{
			String q="SELECT * FROM User WHERE status <> 0";

			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q);

			rs.last();
			countRows = rs.getRow();

			st.close();
			rs.close();
		}catch(SQLException se){
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
		DB_close();
		return countRows;
	}

	public static List<User> listSellers(){

		List<User> seller = new ArrayList<User>();
		User u;
		try{
			Connect();
			String q0="SELECT * FROM Seller";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q0);

			while(rs.next()){
				u = new User();
				u.setID(rs.getInt("id"));
				u.setFirstname(rs.getString("firstname"));
				u.setLastname(rs.getString("lastname"));		
				u.setAddress(rs.getString("address"));
				u.setEmail(rs.getString("email"));
				u.setPhone(rs.getDouble("phone"));
				u.setCompanyName(rs.getString("companyName"));
				u.setAuthorized(rs.getBoolean("authorized"));
				u.setURL(rs.getString("URL"));
				u.setBankAccount(rs.getString("bankAccount"));
				u.setRoutingNumber(rs.getString("routingNumber"));
				u.setPayPalID(rs.getString("payPalID"));
				u.setMiddlename(rs.getString("middlename"));
				u.setType("sel");
				seller.add(u);
			}

			rs.close();
			st.close();

			Iterator<User> itr = seller.iterator();

			while(itr.hasNext()){
				User temp = (User) itr.next();
				q0="SELECT username FROM User WHERE id="+temp.getID();
				st = cn.createStatement();
				rs = st.executeQuery(q0);
				while(rs.next()){
					temp.setUsername(rs.getString("username"));
				}
			}
			rs.close();
			st.close();

		}catch(SQLException se){
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
		DB_close();
		return seller;
	}

	public static List<User> listCustomers(){

		List<User> customer = new ArrayList<User>();
		User u;
		try{
			Connect();
			String q0="SELECT * FROM Customer";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q0);

			while(rs.next()){
				u = new User();
				u.setID(rs.getInt("id"));
				u.setFirstname(rs.getString("firstname"));
				u.setLastname(rs.getString("lastname"));
				u.setAddress(rs.getString("address"));
				u.setEmail(rs.getString("email"));
				u.setPhone(rs.getDouble("phone"));
				u.setPayPalID(rs.getString("payPalID"));
				u.setMiddlename(rs.getString("middlename"));
				u.setType("buy");
				customer.add(u);
			}

			rs.close();
			st.close();

			Iterator<User> itr = customer.iterator();

			while(itr.hasNext()){
				User temp = (User) itr.next();
				q0="SELECT username FROM User WHERE id="+temp.getID();
				st = cn.createStatement();
				rs = st.executeQuery(q0);
				while(rs.next()){
					temp.setUsername(rs.getString("username"));
				}
			}
			rs.close();
			st.close();

		}catch(SQLException se){
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
		DB_close();
		return customer;
	}

	public static List<User> listAdmins(){
		List<User> admin = new ArrayList<User>();
		User u;
		try{
			Connect();
			String q0="SELECT username FROM User WHERE type='adm'";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q0);

			while(rs.next()){
				u = new User();
				u.setType("adm");
				u.setUsername(rs.getString("username"));
				admin.add(u);
			}
			st.close();
			rs.close();
		}catch(SQLException se){
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
		DB_close();
		return admin;
	}
}

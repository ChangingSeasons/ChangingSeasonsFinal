package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static model.ConnectDB.*;

public class AuthDAO {

	public static int checkUserpass(String username, String password){
		int userId=-1;
		Connect();
		try{
			String q="SELECT id FROM User WHERE username='"+username+"' AND password='"+password+"'";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q);
			while(rs.next()){
				userId = Integer.parseInt(rs.getString("id"));
			}
			rs.close();
			st.close();
		}catch(SQLException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();
		return userId;
	}

	public static User getUserbyId(int userID){
		User u = new User();
		String type = "";
		Connect();
		try{

			String q0="SELECT type FROM User WHERE id="+userID;
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q0);
			while(rs.next()){
				type = rs.getString("type");
			}

			st.close();
			rs.close();

			if(type.equalsIgnoreCase("buy")){ // Buyer
				q0="SELECT * FROM Customer WHERE id="+userID;
				st = cn.createStatement();
				rs = st.executeQuery(q0);
				while(rs.next()){
					u.setID(userID);
					u.setFirstname(rs.getString("firstname"));
					u.setLastname(rs.getString("lastname"));
					u.setAddress(rs.getString("address"));
					u.setEmail(rs.getString("email"));
					u.setPhone(rs.getDouble("phone"));
					u.setPayPalID(rs.getString("payPalID"));
					u.setMiddlename(rs.getString("middlename"));
					u.setAuthorized(true);
				}
				rs.close();
				st.close();
				

				q0="SELECT * FROM User WHERE id="+userID;
				st = cn.createStatement();
				rs = st.executeQuery(q0);
				while(rs.next()){
					u.setStatus(rs.getBoolean("status"));
					u.setUsername(rs.getString("username"));
				}
				rs.close();
				st.close();

			}

			else if (type.equalsIgnoreCase("sel")){ // Seller
				q0="SELECT * FROM Seller WHERE id="+userID;
				st = cn.createStatement();
				rs = st.executeQuery(q0);
				while(rs.next()){
					u.setID(userID);
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
				}
				rs.close();
				st.close();

				q0="SELECT * FROM User WHERE id="+userID;
				st = cn.createStatement();
				rs = st.executeQuery(q0);
				while(rs.next()){
					u.setUsername(rs.getString("username"));
					u.setStatus(rs.getBoolean("status"));
				}
				rs.close();
				st.close();

			}

			else{ // Admin
				q0 = "SELECT * FROM User WHERE id="+userID;
				st = cn.createStatement();
				rs = st.executeQuery(q0);
				while(rs.next()){
					u.setID(userID);
					u.setAuthorized(true);
					u.setUsername(rs.getString("username"));
					u.setStatus(rs.getBoolean("status"));
				}
				rs.close();
				st.close();
			}

		}catch(SQLException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();
		u.setType(type);
		return u;
	}

	public static int enterNewuser(String username, String password, String type){

		int ID = -1;

		try{
			if(isUsernameAvailable(username)==true){
				Connect();
				String q0 = "Select id from User";
				Statement st = cn.createStatement();
				ResultSet rs = st.executeQuery(q0);

				if(rs.next()){
					rs.last(); // Get ID of last User
					ID = rs.getInt("id");
					ID++;
				}
				else
					ID=1; // Empty Table, so start with ID 1

				rs.close();
				st.close();

				String q1 = "INSERT into User (id, username, password, type, status)" + " values (?, ?, ?, ?, ?)";
				PreparedStatement ps = cn.prepareStatement(q1);
				ps.setInt(1, ID);
				ps.setString (2,username);
				ps.setString (3,password);
				ps.setString(4, type);
				ps.setBoolean(5, true); // Account is active, by default, unless otherwise user itself chose to delete it
				ps.executeUpdate();
				ps.close();

			}
		}catch(SQLException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}	
		DB_close();
		return ID;
	}

	public static boolean enterUsernameSeller(int userID, double phone, boolean authorized, String... args){
		Connect();
		try{
			String companyName = args[0], address = args[1],
					email = args[2], URL = args[3],
					bankAccount = args[4], routingNumber = args[5],
					firstname = args[6], middlename = args[7], lastname = args[8], payPalID = args[9];

			String q1 = "INSERT into Seller (id, companyName, address, email, phone, authorized, URL, bankAccount, routingNumber, firstname, middlename, lastname, payPalID)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = cn.prepareStatement(q1);
			ps.setInt(1, userID);
			ps.setString(2, companyName);
			ps.setString(3, address);
			ps.setString(4, email);
			ps.setDouble(5, phone);
			ps.setBoolean(6, authorized);
			ps.setString(7, URL);
			ps.setString(8, bankAccount);
			ps.setString(9, routingNumber);
			ps.setString (10, firstname);
			ps.setString(11, middlename);
			ps.setString (12, lastname);
			ps.setString(13, payPalID);
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();
		return true;
	}

	public static boolean enterUsernameBuyer(int userID, String firstname, String lastname, String address, String email, double phone, String payPalID, String middlename){
		Connect();
		try{
			String q1 = "INSERT into Customer (id, firstName, lastName, address, email, phone, payPalID, middlename)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = cn.prepareStatement(q1);
			ps.setInt(1, userID);
			ps.setString (2, firstname);
			ps.setString (3, lastname);
			ps.setString(4, address);
			ps.setString(5, email);
			ps.setDouble(6, phone);
			ps.setString(7, payPalID);
			ps.setString(8, middlename);
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();
		return true;
	}

	public static boolean isUsernameAvailable(String username){
		Connect();
		boolean flag = true;
		try{
			String q="SELECT username FROM User";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q);
			while(rs.next()){
				if(rs.getString("username").equals(username))
					flag = false;
			}
			rs.close();
			st.close();
		}catch(SQLException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();
		return flag;
	}

	public static String getPassword (String email, double phone){
		Connect();
		String password = "";
		int id = -1;
		int flag = 0;
		try{
			String q = "SELECT id FROM Customer WHERE email='"+email+"' AND phone="+phone;
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q);
			while(rs.next()){
				id = rs.getInt("id");
			}
			if(id != -1){
				String q1 = "SELECT password FROM User WHERE id="+id;
				st = cn.createStatement();
				rs = st.executeQuery(q1);
				while(rs.next())
					password = rs.getString("password");
				rs.close();
				st.close();
				flag = 1;
			}
		}catch(SQLException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();
		if(flag==1)
			return password;
		return null;
	}

	public static boolean resetPassword(String username, String password){
		Connect();
		try{
			String q = "UPDATE User SET password='"+password+"' WHERE username='"+username+"'";
			Statement st = cn.createStatement();
			st.executeUpdate(q);

			st.close();
		}catch(SQLException sql){
			System.err.println(sql.getMessage());
			sql.printStackTrace();
		}
		DB_close();
		return true;
	}

	public static boolean updateSeller(int userID, double phone, String... args){
		Connect();
		try{
			String companyName = args[0], address = args[1],
					email = args[2], bankAccount = args[3], routingNumber = args[4],
					firstname = args[5], middlename = args[6], lastname = args[7], payPalID = args[8];

			String q = "UPDATE Seller SET companyName='"+companyName+"', address='"+address+
					"', email='"+email+"', phone="+phone+", bankAccount='"+bankAccount+
					"', routingNumber='"+routingNumber+"', firstname='"+firstname+
					"', middlename='"+middlename+"', lastname='"+lastname+"', payPalID='"+payPalID+"' WHERE id="+userID;

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

	public static boolean updateBuyer(int userID, String firstname, String lastname, String address, String email, double phone, String payPalID, String middlename){
		Connect();
		try{
			String q = "UPDATE Customer SET firstname='"+firstname+"', lastname='"+lastname+
					"', address='"+address+"', email='"+email+"', phone="+phone+", payPalID='"+payPalID+
					"', middlename='"+middlename+"' WHERE id="+userID;

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

}

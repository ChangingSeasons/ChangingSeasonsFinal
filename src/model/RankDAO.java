package model;
import static model.ConnectDB.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RankDAO {

	public static boolean addRank(int productID, int customerID, int rank){
		Connect();

		try{
			String q1 = "INSERT into productRank (productID, customerID, rank)" + " values (?, ?, ?)";
			PreparedStatement ps = cn.prepareStatement(q1);
			ps.setInt(1, productID);
			ps.setInt(2, customerID);
			ps.setInt(3, rank);
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();
		return true;
	}

	public static boolean updateReview(int productID, int customerID, String review){
		Connect();

		try{
			String q = "UPDATE productRank SET review='"+review+"' WHERE productID="+productID+" AND customerID="+customerID;
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

	public static boolean editRank(int... args){
		Connect();
		int productID = args[0], customerID = args[1], rank = args[2];

		try{
			String q = "UPDATE productRank SET rank="+rank+" WHERE productID="+productID+" AND customerID="+customerID;
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

	public static int viewRank(int productID, int customerID){
		Connect();
		int rank = -1;
		try{
			String q0 = "SELECT rank FROM productRank WHERE productID="+productID+" AND customerID="+customerID;
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q0);

			while(rs.next())
				rank = rs.getInt("rank");

			rs.close();
			st.close();
		}catch(SQLException se){
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
		DB_close();

		return rank;
	}

	public static List<String> viewReview(int productID){ // View all Reviews of a Single Product
		Connect();
		List<String> review = new ArrayList<String>();
		try{
			String q0 = "SELECT review FROM productRank WHERE productID="+productID;
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q0);

			while(rs.next())
				review.add(rs.getString("review"));

			rs.close();
			st.close();
		}catch(SQLException se){
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
		DB_close();
		return review;
	}

	public static List<String> viewCustomerSpecificReviews(int customerID){ // Customer can See all products reviewed
		List<String> reviews = new ArrayList<String>();
		Connect();

		try{
			String q0 = "SELECT review FROM productRank WHERE customerID="+customerID;
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q0);

			while(rs.next())
				reviews.add(rs.getString("review"));
			rs.close();
			st.close();
		}catch(SQLException se){
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
		DB_close();
		return reviews;
	}

	public static String customerViewProductReview(int customerID, int productID) {
		String review="";
		Connect();
		try{
			String q0 = "SELECT review FROM productRank WHERE customerID="+customerID+" AND productID="+productID;
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q0);

			while(rs.next())
				review = rs.getString("review");
			rs.close();
			st.close();
		}catch(SQLException se){
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
		DB_close();
		return review;
	}

	public static double averageRank(int productID){
		List<Integer> rank = new ArrayList<Integer>();
		Connect();
		double average = 0;
		try{
			String q0 = "SELECT rank FROM productRank WHERE productID="+productID;
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q0);

			while(rs.next())
				rank.add(rs.getInt("rank"));
			rs.close();
			st.close();

			for(Integer i : rank)
				average += i;

		}catch(SQLException se){
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
		DB_close();
		return (average/rank.size());
	}

}

package model;

import static model.ConnectDB.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

	public static int getlastUpdate(){
		Connect();
		int ID = -1;
		try{
			String q0 = "SELECT productID FROM Product ORDER BY productID DESC LIMIT 1";
			Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(q0);

			//rs.last(); // Get ID of last Product
			if(rs.next())
				ID = rs.getInt("productID");

			rs.close();
			st.close();
		}catch(SQLException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();
		System.out.println("ID of last added product:"+ID);
		return ID;
	}

	public static boolean addImage(String imagepath, int ID){
		//int productID = getlastUpdate();
		try{
			Connect();
			String q = "UPDATE Product SET imagePath='"+imagepath+"' WHERE productID="+ID;

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

	public static int insertProduct(String productName, String productDesc, int sellerID, float price, String imagepath, float shippingCost,  String imageName, String type){
		int productID = getID();
		try{
			
			String size= "false false false false false", color = "false false false false false";
			
			Connect();
			String q1 = "INSERT into Product (productID, productName, productDesc, sellerID, price, imagePath, shippingCost, size, color, imageName, type, status)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = cn.prepareStatement(q1);
			ps.setInt(1, productID);
			System.out.println("productID from INSERTPRODUCT FUNCTION:"+productID);
			ps.setString(2, productName);
			ps.setString(3, productDesc);
			ps.setInt(4, sellerID);
			ps.setFloat(5, price);
			ps.setString(6, imagepath);
			ps.setFloat(7, shippingCost);
			ps.setString(8, size);
			ps.setString(9, color);
			ps.setString(10, imageName);
			ps.setString(11, type);
			ps.setBoolean(12, true);
			ps.executeUpdate();

			ps.close();

		}catch(SQLException se){
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
		DB_close();
		return productID;
	}

	public static boolean editProduct(int productID, float price, float shippingCost, String productName, String productDesc, String imageName, String type){
		Connect();
		try{

			String q = "UPDATE Product SET productName='"+productName+"', productDesc='"+productDesc
					+"', price="+price+", shippingCost="+shippingCost+", imageName='"+imageName+"', type='"+type+"' WHERE productID="+productID;

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

	public static boolean deleteProduct(int productID){
		Connect();

		try{
			String q = "UPDATE Product SET status=0 WHERE productID="+productID;
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

	private static int getID(){
		Connect();
		int ID = -1;
		try{
			String q0 = "SELECT productID FROM Product ORDER BY productID DESC LIMIT 1";
			Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery(q0);

			if(rs.next()){
				//rs.last(); // Get ID of last Product
				ID = rs.getInt("productID");
				ID++;
			}
			else
				ID=1; // Empty Table, so start with ID 1

			
			System.out.println("\nID from GETID After Query+"+ID);
			
			rs.close();
			st.close();
		}catch(SQLException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();
		System.out.println("ID from GETID in ProductDAO BEFORE RETURN"+ID);
		return ID;
	}

	public static int noOfProducts(){

		Connect();
		int countRows = 0;
		try{
			String q="SELECT productID FROM Product WHERE status <> 0 ORDER BY productID DESC LIMIT 1";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q);

			//rs.last();
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

	public static List<Product> getProducts(int... ID) {
		List<Product> productList = new ArrayList<Product>();

		int sellerID = 0;
		String q0;
		try{

			if(ID.length>0){ // List products by seller
				sellerID = ID[0];
				q0="SELECT * FROM Product WHERE sellerID="+sellerID+" AND status <> 0";
			}
			else // List all products
				q0="SELECT * FROM Product WHERE status <> 0";
			Connect();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q0);
			while(rs.next()){
				Product p = new Product();
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
				p.setCompanyName(getCompanyNameForProduct(rs.getInt("sellerID")));
				Connect();
				productList.add(p);
			}

			st.close();
			rs.close();

		}catch(SQLException se){
			System.err.println(se.getMessage());
			se.printStackTrace();
		}

		DB_close();

		return productList;
	}
	
	public static String getCompanyNameForProduct(int sellerID){
		String companyName = "";
		try{
			Connect();
			String q0 = "SELECT companyName FROM Seller WHERE id="+sellerID;
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q0);
			
			while(rs.next())
				companyName = (rs.getString("companyName"));
			
			rs.close();
			st.close();
			
		}catch(SQLException se){
			System.err.println(se.getMessage());
			se.printStackTrace();
		}

		DB_close();
		return companyName;
	}

	public static Product viewProduct(int productID){
		Connect();
		Product p = null;
		try{
			String q0="SELECT * FROM Product WHERE productID="+productID+" AND status <> 0";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q0);
			while(rs.next()){
				p = new Product();
				p.setProductID(productID);
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
				p.setCompanyName(getCompanyNameForProduct(rs.getInt("sellerID")));
				Connect();
				
			}
			st.close();
			rs.close();
		}catch(SQLException se){
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
		DB_close();
		return p;
	}

	public static List<Product> search(String... search){
		Connect();

		List<Product> products = new ArrayList<Product>();
		String q0 = "";
		try{
			if(search.length==1)
				q0="SELECT * FROM Product WHERE productName like '%"+search[0]+"%' OR productDesc like '%"+search[0]+"%'";
			else if(search.length==2)
				q0="SELECT * FROM Product WHERE productName like '%"+search[0]+"%' OR productName like '%"+search[1]+"%' OR productDesc like '%"+search[0]+"%' OR productDesc like '%"+search[1]+"%'";

			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q0);


			while(rs.next()){
				Product p = new Product();
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
				p.setCompanyName(getCompanyNameForProduct(rs.getInt("sellerID")));
				Connect();
				products.add(p);
			}

			st.close();
			rs.close();
		}catch(SQLException se){
			System.err.println(se.getMessage());
			se.printStackTrace();
		}

		DB_close();
		return products;
	}


	public static List<Product> lastFour(){

		List<Product> products = new ArrayList<Product>();
		Connect();
		try{
			String q0 = "SELECT * FROM Product ORDER BY productID DESC LIMIT 0,4;";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q0);

			while(rs.next()){
				Product p = new Product();
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
				p.setCompanyName(getCompanyNameForProduct(rs.getInt("sellerID")));
				Connect();
				products.add(p);
			}

			st.close();
			rs.close();
		}catch(SQLException se){
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
		DB_close();
		return products;
	}

}

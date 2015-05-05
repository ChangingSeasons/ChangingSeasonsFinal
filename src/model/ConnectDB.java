package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	static Connection cn;

	public static void Connect(){
		try{

			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost/SEProject", "root", "pass");
		}catch(SQLException sql){
			System.err.println(sql.getMessage());
			sql.printStackTrace();
		}catch(ClassNotFoundException c){
			System.err.println(c.getMessage());
			c.printStackTrace();
		}
	}

	public static void DB_close(){
		try{
			if(cn!=null)
				cn.close();
		}catch(SQLException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
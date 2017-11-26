package com.MovRec.DAO;
import java.sql.*;
 
public class DBConnectionDAO {
	
	public Connection createSQLConnection(){  
		Connection con = null;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			 con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/24frames","root","divya14");  
			//here 24frames is database name, root is username and password  
			 
		}catch(SQLException e){
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return con;  
	}  
}

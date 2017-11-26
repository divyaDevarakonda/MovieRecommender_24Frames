package com.MovRec.DAO;
import java.sql.*;
import java.util.ArrayList;

public class DAOUtilities {

	public boolean validateTable(String tableName) throws SQLException{
		boolean exists = false;
		DBConnectionDAO dbConnection=null;
		Connection con=null;
		try{
		dbConnection = new DBConnectionDAO();
		con = dbConnection.createSQLConnection();
		
		DatabaseMetaData md = con.getMetaData();
		ResultSet rs = md.getTables(null, null, "%", null);
		ArrayList<String> tables = new ArrayList<String>();
		while (rs.next()) {
			tables.add(rs.getString(3));
		}
		if(tables.contains(tableName)){
			exists = true;
		}
		}catch(Exception e){
			e.getMessage();
			con.close();
		}finally{
			con.close();
		}
		return exists;
	}
	public int getColumnCount(Connection con,String TableName) throws SQLException{
		int colCount=0;
//		DBConnectionDAO dbConnection=null;
//		Connection con=null;
		try{
//		dbConnection = new DBConnectionDAO();
//		con = dbConnection.createSQLConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from "+TableName);  
		ResultSetMetaData rsmd=  rs.getMetaData();
		colCount=rsmd.getColumnCount();
		}catch(Exception e){
			e.getMessage();
		}
		
		return colCount;
	}
	public ArrayList<String> getColumnName(Connection con,String TableName) throws SQLException{
		ArrayList<String> colNames = new ArrayList<String>();
		try{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from "+TableName);  
		ResultSetMetaData rsmd=  rs.getMetaData();
		int colCount=rsmd.getColumnCount();
		for(int i=1;i<=colCount;i++){
			colNames.add(rsmd.getColumnName(i));
		}
		}catch(Exception e){
			e.getMessage();
		}
		
		return colNames;
	}
	

	public ArrayList<String> getColumnType(Connection con,String TableName) throws SQLException{
		ArrayList<String> colTypes = new ArrayList<String>();
		try{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from "+TableName);  
		ResultSetMetaData rsmd=  rs.getMetaData();
		int colCount=rsmd.getColumnCount();
		for(int i=1;i<=colCount;i++){
			colTypes.add(rsmd.getColumnTypeName(i));
		}
		}catch(Exception e){
			e.getMessage();
		}
		return colTypes;
	}
	
	public  boolean prepareInsertStatement(String[] eachLineData,String tableName) {
		DBConnectionDAO dbConnection = null;
		Connection con = null;
		boolean inserted = false;
		String instStmt = "insert into "+tableName+ "(";
		try{
		dbConnection = new DBConnectionDAO();
		con = dbConnection.createSQLConnection();
		ArrayList<String> columnNames = getColumnName(con,tableName);
		ArrayList<String> columnType = getColumnType(con,tableName);
		String[] colValues = prepareColumnValues(con,tableName,eachLineData);
		
		for(String col: columnNames){
			instStmt=instStmt + col +",";		
		}
		instStmt = instStmt.substring(0, instStmt.length()-1);
		instStmt= instStmt + ") values(";
		
		for(int i=0;i<columnType.size();i++){
			instStmt=instStmt +"?,";
		}
		instStmt = instStmt.substring(0, instStmt.length()-1);
		instStmt = instStmt +");";
		
		PreparedStatement preparedStatement = con.prepareStatement(instStmt);
		for(int i=0;i<colValues.length;i++){
			if(columnType.get(i).equalsIgnoreCase("varchar") || columnType.get(i).equalsIgnoreCase("datetime")){
				if(colValues[i] == null || colValues[i].equalsIgnoreCase("\\N") ||  colValues[i].equalsIgnoreCase("null")){
					preparedStatement.setString(i+1, null);
					}else{
						preparedStatement.setString(i+1, colValues[i]);	
					}
			}else if(columnType.get(i).equalsIgnoreCase("int")){
				if( colValues[i] == null || colValues[i].equalsIgnoreCase("\\N") || colValues[i].equalsIgnoreCase("null")){
					preparedStatement.setNull(i+1, java.sql.Types.INTEGER);
				}
				else{
					preparedStatement.setInt(i+1, Integer.parseInt(colValues[i]));
				}
			}else if(columnType.get(i).equalsIgnoreCase("float")){
				if(colValues[i] == null || colValues[i].equalsIgnoreCase("\\N")  || colValues[i].equalsIgnoreCase("null")) {
					preparedStatement.setNull(i+1, java.sql.Types.FLOAT);
				}
				else{
					preparedStatement.setFloat(i+1, Float.parseFloat(colValues[i]));
				}
				
			}
//			if(columnType.get(i).equalsIgnoreCase("datetime")){
//				preparedStatement.set(i+1, eachLineData[i]);	
//			}
		}
		
		preparedStatement.executeUpdate();
		inserted= true;
		
		}catch(Exception e){
			e.getMessage();
			System.out.println("error orrured is divya : "+e.getMessage());
			inserted = false;
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return inserted;
	}
	
	public String[] prepareColumnValues(Connection con,String tableName,String[] eachLineData) throws SQLException{
	int colCount = getColumnCount(con,tableName);
	String[] colValues = new String[colCount];
	for(int i=0;i<eachLineData.length;i++){
		colValues[i] = eachLineData[i];
	}
		return colValues;
	}
	public boolean insertData(String insertStmt){
		boolean inserted = false;
		DBConnectionDAO dbConnection = null;
		Connection con = null;
		try{
			dbConnection = new DBConnectionDAO();
			con = dbConnection.createSQLConnection();
		}catch(Exception e){
			
		}
		return inserted;
	}
}

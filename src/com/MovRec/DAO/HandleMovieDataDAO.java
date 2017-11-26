package com.MovRec.DAO;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.MovRec.Service.DataFileReaderService;


public class HandleMovieDataDAO {

	public void insertDataIntoTables(){
	try{  
		DataFileReaderService dataFileService = new DataFileReaderService();
		DAOUtilities daoUtilities = new DAOUtilities();
		boolean insertStmt=false;
		ArrayList<String[]> eachFileData = dataFileService.readEachFile("/Users/divyadevarakonda/Documents/204/RTD/movie_locations.dat");
		for(String[] eachLine:eachFileData){
			insertStmt=daoUtilities.prepareInsertStatement(eachLine,"movie_locations");
			if(insertStmt){
				System.out.println("yess");
			}
		}

	}catch(Exception e){ 
		System.out.println(e);
		}  
	}

	public static void main(String args[]) throws SQLException{
		int k = 5;
		HandleMovieDataDAO handleMoviedata = new HandleMovieDataDAO();
		ArrayList<HashMap<String, String>> topkMoviesList =handleMoviedata.getTopkMoviesbyAudianceScore(k);
	}
	
	public ArrayList<HashMap<String, String>> getTopkMoviesbyAudianceScore(int userInputk) throws SQLException {
		DBConnectionDAO dbConnection = null;
		Connection con = null;
		ArrayList<HashMap<String, String>> topkMoviesList  = null;
		try{
		dbConnection = new DBConnectionDAO();
		con = dbConnection.createSQLConnection();
		//String[] resultRow = new String[5];
		String selectStmt = "select title,year,rtAudienceScore,rtPictureURL,imdbPictureURL from movies order "
				+ "by rtAudienceScore desc limit ?";
		PreparedStatement preparedStatement = con.prepareStatement(selectStmt);
		preparedStatement.setInt(1, userInputk);
		ResultSet rs = preparedStatement.executeQuery();
		topkMoviesList = new ArrayList<HashMap<String, String>>();
		while (rs.next()) {
			 HashMap<String,String> row = new HashMap<String,String>(5);
			 row.put("title", rs.getString("title"));
			 row.put("year", rs.getString("year"));
			 row.put("rtAudienceScore", rs.getString("rtAudienceScore"));
			 row.put("rtPictureURL", rs.getString("rtPictureURL"));
			 row.put("imdbPictureURL", rs.getString("imdbPictureURL"));
			 topkMoviesList.add(row);
		
		}
		}catch(Exception e){
			e.getMessage();
		}
		return topkMoviesList;
	}
	

}

package com.MovRec.DAO;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.MovRec.Bean.MovieDetailsBean;
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

//	public static void main(String args[]) throws SQLException{
//		int k = 5;
//		HandleMovieDataDAO handleMoviedata = new HandleMovieDataDAO();
//		ArrayList<MovieDetailsBean> topkMoviesList =handleMoviedata.getTopkMoviesbyAudianceScore(k);
//	}
	
	public ArrayList<MovieDetailsBean> getTopkMoviesbyAudianceScore(int userInputk) throws SQLException {
		DBConnectionDAO dbConnection = null;
		Connection con = null;
		ArrayList<MovieDetailsBean> topkMoviesList  = null;
		MovieDetailsBean movieDetails = new MovieDetailsBean();
		try{
		dbConnection = new DBConnectionDAO();
		con = dbConnection.createSQLConnection();
		String selectStmt = "select title,year,rtAudienceScore,rtPictureURL,imdbPictureURL from movies order "
				+ "by rtAudienceScore desc limit ?";
		PreparedStatement preparedStatement = con.prepareStatement(selectStmt);
		preparedStatement.setInt(1, userInputk);
		ResultSet rs = preparedStatement.executeQuery();
		topkMoviesList = new ArrayList<MovieDetailsBean>();
		while (rs.next()) {
			movieDetails = new MovieDetailsBean();
			movieDetails.setTitle(rs.getString("title"));
			movieDetails.setYear(rs.getString("year"));
			movieDetails.setRtAudienceScore(rs.getString("rtAudienceScore"));
			movieDetails.setRtPictureURL(rs.getString("rtPictureURL"));
			movieDetails.setImdbPictureURL(rs.getString("imdbPictureURL"));
			
			topkMoviesList.add(movieDetails);
		
		}
		}catch(Exception e){
			e.getMessage();
		}
		return topkMoviesList;
	}
	
	public ArrayList<MovieDetailsBean> getMovieDetailsByName(String movieName) throws SQLException {  // q2
		DBConnectionDAO dbConnection = null;
		Connection con = null;
		ArrayList<MovieDetailsBean> movDetails  = null;
		MovieDetailsBean movieDetails = new MovieDetailsBean();
		try{
		dbConnection = new DBConnectionDAO();
		con = dbConnection.createSQLConnection();
		movieName = "%" + movieName + "%";
		String selectStmt = "SELECT m.title, m.year, m.rtPictureURL, m.rtAudienceScore, m.imdbPictureURL, t.value "
				+ "FROM user_taggedmovies u, movies m, tags t " + 
				"WHERE u.movieID = m.Id and u.tagID = t.Id and m.title like ?";
		PreparedStatement preparedStatement = con.prepareStatement(selectStmt);
		preparedStatement.setString(1, movieName);
		ResultSet rs = preparedStatement.executeQuery();
		movDetails = new ArrayList<MovieDetailsBean>();
		while (rs.next()) {
			movieDetails = new MovieDetailsBean();
			movieDetails.setTitle(rs.getString("title"));
			movieDetails.setYear(rs.getString("year"));
			movieDetails.setRtAudienceScore(rs.getString("rtAudienceScore"));
			movieDetails.setRtPictureURL(rs.getString("rtPictureURL"));
			movieDetails.setImdbPictureURL(rs.getString("imdbPictureURL"));
			movieDetails.setUserTags(rs.getString("value"));
			movDetails.add(movieDetails);
		
		}
		}catch(Exception e){
			e.getMessage();
		}
		return movDetails;
	}
	
	public ArrayList<MovieDetailsBean> getTopkMoviesByGenre(int userInputk,String genre) throws SQLException { //q3
		DBConnectionDAO dbConnection = null;
		Connection con = null;
		ArrayList<MovieDetailsBean> topkMovieGenreList  = null;
		MovieDetailsBean movieDetails = new MovieDetailsBean();
		try{
		dbConnection = new DBConnectionDAO();
		con = dbConnection.createSQLConnection();
		String selectStmt = "SELECT m.title, m.year, m.rtPictureURL, m.rtAudienceScore, m.imdbPictureURL " +
					"FROM movies m, movie_genres mg " +
					"WHERE m.Id = mg.movieID and mg.genre like ? and m.rtAudienceScore <> \"\\\\n\"\r\n " + 
					"order by m.rtAudienceScore DESC " +
					"Limit ?";
		PreparedStatement preparedStatement = con.prepareStatement(selectStmt);
		preparedStatement.setString(1,"%"+ genre  + "%");
		preparedStatement.setInt(2, userInputk);
		ResultSet rs = preparedStatement.executeQuery();
		topkMovieGenreList = new ArrayList<MovieDetailsBean>();
		while (rs.next()) {
			movieDetails = new MovieDetailsBean();
			movieDetails.setTitle(rs.getString("title"));
			movieDetails.setYear(rs.getString("year"));
			movieDetails.setRtAudienceScore(rs.getString("rtAudienceScore"));
			movieDetails.setRtPictureURL(rs.getString("rtPictureURL"));
			movieDetails.setImdbPictureURL(rs.getString("imdbPictureURL"));
		
			topkMovieGenreList.add(movieDetails);
		
		}
		}catch(Exception e){
			e.getMessage();
		}
		return topkMovieGenreList;
	}
	
	public ArrayList<MovieDetailsBean> getTopkMoviesByDirectorName(String directorName) throws SQLException { //q4
		DBConnectionDAO dbConnection = null;
		Connection con = null;
		ArrayList<MovieDetailsBean> topkMovieBDirectorName  = null;
		MovieDetailsBean movieDetails = new MovieDetailsBean();
		try{
		dbConnection = new DBConnectionDAO();
		con = dbConnection.createSQLConnection();
		String selectStmt = "Select m.title, m.year, m.rtPictureURL, m.rtAudienceScore, m.imdbPictureURL " +
		             "FROM movies m, movie_directors md " +
				      "WHERE m.Id = md.movieID and md.directorName like ?";
		             
		PreparedStatement preparedStatement = con.prepareStatement(selectStmt);
		preparedStatement.setString(1, "%" +directorName +"%");
		ResultSet rs = preparedStatement.executeQuery();
		topkMovieBDirectorName  = new ArrayList<MovieDetailsBean>();
	    
		while (rs.next()) {
			
			movieDetails = new MovieDetailsBean();
			movieDetails.setTitle(rs.getString("title"));
			movieDetails.setYear(rs.getString("year"));
			movieDetails.setRtAudienceScore(rs.getString("rtAudienceScore"));
			movieDetails.setRtPictureURL(rs.getString("rtPictureURL"));
			movieDetails.setImdbPictureURL(rs.getString("imdbPictureURL"));
			
			topkMovieBDirectorName .add(movieDetails);
		
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return topkMovieBDirectorName;
	}
	
	public ArrayList<MovieDetailsBean> getTopkMoviesByActorName(String actorName) throws SQLException { //q5
		DBConnectionDAO dbConnection = null;
		Connection con = null;
		ArrayList<MovieDetailsBean> topkMovieBActorName  = null;
		MovieDetailsBean movieDetails = new MovieDetailsBean();
		try{
		dbConnection = new DBConnectionDAO();
		con = dbConnection.createSQLConnection();
		String selectStmt = "Select m.title, m.year, m.rtPictureURL, m.rtAudienceScore, m.imdbPictureURL " +
		             "FROM movies m, movie_actors ma " +
				      "WHERE m.Id = ma.movieID and ma.actorName like ?";
		             
		PreparedStatement preparedStatement = con.prepareStatement(selectStmt);
		preparedStatement.setString(1,"%"+ actorName +"%");
		ResultSet rs = preparedStatement.executeQuery();
		topkMovieBActorName  = new ArrayList<MovieDetailsBean>();
	    
		while (rs.next()) {
			
			movieDetails = new MovieDetailsBean();
			movieDetails.setTitle(rs.getString("title"));
			movieDetails.setYear(rs.getString("year"));
			movieDetails.setRtAudienceScore(rs.getString("rtAudienceScore"));
			movieDetails.setRtPictureURL(rs.getString("rtPictureURL"));
			movieDetails.setImdbPictureURL(rs.getString("imdbPictureURL"));
			
			topkMovieBActorName .add(movieDetails);
		
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return topkMovieBActorName;
	}
	
	public ArrayList<MovieDetailsBean> getTopkMoviesByTagName(String tagName) throws SQLException { //q6
		DBConnectionDAO dbConnection = null;
		Connection con = null;
		ArrayList<MovieDetailsBean> topkMovieBTagName  = null;
		MovieDetailsBean movieDetails = new MovieDetailsBean();
		try{
		dbConnection = new DBConnectionDAO();
		con = dbConnection.createSQLConnection();
		String selectStmt = "Select m.title, m.year, m.rtPictureURL, m.rtAudienceScore, m.imdbPictureURL " +
		             "FROM movies m, tags t, movie_tags mt " +
				      "WHERE m.Id = mt.movieID and t.Id = mt.tagID and t.value like ? and m.rtAudienceScore <> \"\\\\n\" " +
		              "order by m.rtAudienceScore DESC";
		             
		PreparedStatement preparedStatement = con.prepareStatement(selectStmt);
		preparedStatement.setString(1,"%" + tagName +"%");
		ResultSet rs = preparedStatement.executeQuery();
		topkMovieBTagName  = new ArrayList<MovieDetailsBean>();
	    
		while (rs.next()) {
			
			movieDetails = new MovieDetailsBean();
			movieDetails.setTitle(rs.getString("title"));
			movieDetails.setYear(rs.getString("year"));
			movieDetails.setRtAudienceScore(rs.getString("rtAudienceScore"));
			movieDetails.setRtPictureURL(rs.getString("rtPictureURL"));
			movieDetails.setImdbPictureURL(rs.getString("imdbPictureURL"));
			
			topkMovieBTagName .add(movieDetails);
		
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return topkMovieBTagName;
	}	
	
	public ArrayList<String> getTopPopularDirectors(int userInputk) throws SQLException { //q7
		DBConnectionDAO dbConnection = null;
		Connection con = null;
		ArrayList<String> topPopularDirectors  = null;
		try{
		dbConnection = new DBConnectionDAO();
		con = dbConnection.createSQLConnection();
		String selectStmt = "Select md.directorName " +
							"From movies m, movie_directors md " +
							"Where m.Id = md.movieID " +
							"group by md.directorId " +
							"having count(md.directorId)>=10 " +
							"order by avg(m.rtAudienceScore) DESC " +
							"limit ? ";
		              
		PreparedStatement preparedStatement = con.prepareStatement(selectStmt);
		preparedStatement.setInt(1,userInputk);
		ResultSet rs = preparedStatement.executeQuery();
		topPopularDirectors  = new ArrayList<String>();
	    
		while (rs.next()) {
			
			//movieDetails = new MovieDetailsBean();
			String s=rs.getString("directorName");
			
			topPopularDirectors .add(s);
		
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return topPopularDirectors;
	}
	
	public ArrayList<String> getTopPopularActors(int userInputk) throws SQLException { //q8
		DBConnectionDAO dbConnection = null;
		Connection con = null;
		ArrayList<String> getTopPopularActors  = null;
		try{
		dbConnection = new DBConnectionDAO();
		con = dbConnection.createSQLConnection();
		String selectStmt = "Select ma.actorName,ma.actorId " +
							"From movies m, movie_actors ma " +
							"Where m.Id = ma.movieID " +
							"group by ma.actorId " +
							"having count(ma.actorId)>=10 " +
							"order by avg(m.rtAudienceScore) DESC " +
							"limit ? ";
		              
		PreparedStatement preparedStatement = con.prepareStatement(selectStmt);
		preparedStatement.setInt(1,userInputk);
		ResultSet rs = preparedStatement.executeQuery();
		getTopPopularActors  = new ArrayList<String>();
	    
		while (rs.next()) {
			
			//movieDetails = new MovieDetailsBean();
			String s=rs.getString("actorName");
			
			getTopPopularActors.add(s);
		
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return getTopPopularActors;
		
		
	}
	
	public ArrayList<String> getTagName(String title) throws SQLException { //q10
		DBConnectionDAO dbConnection = null;
		Connection con = null;
		ArrayList<String> getTagName  = null;
		try{
		dbConnection = new DBConnectionDAO();
		con = dbConnection.createSQLConnection();
		String selectStmt = "Select distinct t.value	 " +
							"From tags t, movie_tags mt, movies m " +
							"Where t.id = mt.movieID and mt.movieID = m.id and m.title like ? ";
		              
		PreparedStatement preparedStatement = con.prepareStatement(selectStmt);
		preparedStatement.setString(1, "%" + title + "%");
		ResultSet rs = preparedStatement.executeQuery();
		getTagName  = new ArrayList<String>();
	    
		while (rs.next()) {
			
			//movieDetails = new MovieDetailsBean();
			String s=rs.getString("value");
			
			getTagName .add(s);
		
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return getTagName;
		
		
	}

}

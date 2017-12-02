package com.MovRec.Action;

import java.util.ArrayList;
import java.util.HashMap;

import com.MovRec.Bean.MovieDetailsBean;
import com.MovRec.DAO.HandleMovieDataDAO;
import com.opensymphony.xwork2.ActionSupport;

public class UserInputAction extends ActionSupport {
	
	 private String userInputk;
	 private String userInputMovie;
	 ArrayList<MovieDetailsBean> topkMoviesListbyScore = new ArrayList<MovieDetailsBean>();
	 ArrayList<MovieDetailsBean> moviesByName = new ArrayList<MovieDetailsBean>();
	 
	
	public String execute() throws Exception {
		   System.out.println("im here");
		   HandleMovieDataDAO handleMoviedata = new HandleMovieDataDAO();
		   if(userInputk!=null)
		   topkMoviesListbyScore =handleMoviedata.getTopkMoviesbyAudianceScore(Integer.parseInt(userInputk));
		   if(userInputMovie!=null)
		   moviesByName =handleMoviedata.getMovieDetailsByName(userInputMovie);
		   
	       return "success";
	   }
	

	public String getUserInputk() {
		return userInputk;
	}

	public void setUserInputk(String userInputk) {
		this.userInputk = userInputk;
	}


	public String getUserInputMovie() {
		return userInputMovie;
	}


	public void setUserInputMovie(String userInputMovie) {
		this.userInputMovie = userInputMovie;
	}


	public ArrayList<MovieDetailsBean> getTopkMoviesListbyScore() {
		return topkMoviesListbyScore;
	}


	public void setTopkMoviesListbyScore(ArrayList<MovieDetailsBean> topkMoviesListbyScore) {
		this.topkMoviesListbyScore = topkMoviesListbyScore;
	}


	public ArrayList<MovieDetailsBean> getMoviesByName() {
		return moviesByName;
	}


	public void setMoviesByName(ArrayList<MovieDetailsBean> moviesByName) {
		this.moviesByName = moviesByName;
	}
	
	   
  
	
	
}

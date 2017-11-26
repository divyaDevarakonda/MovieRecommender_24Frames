package com.MovRec.Action;

import java.util.ArrayList;
import java.util.HashMap;

import com.MovRec.DAO.HandleMovieDataDAO;
import com.opensymphony.xwork2.ActionSupport;

public class UserInputAction extends ActionSupport {
	
	 private String userInputk;
	 ArrayList<HashMap<String, String>> topkMoviesList = new ArrayList<HashMap<String, String>>();
	


	public String execute() throws Exception {
		   System.out.println("im here");
		   HandleMovieDataDAO handleMoviedata = new HandleMovieDataDAO();
		   topkMoviesList =handleMoviedata.getTopkMoviesbyAudianceScore(Integer.parseInt(userInputk));
	       return "success";
	   }

	public String getUserInputk() {
		return userInputk;
	}

	public void setUserInputk(String userInputk) {
		this.userInputk = userInputk;
	}
	
	   public ArrayList<HashMap<String, String>> getTopkMoviesList() {
		return topkMoviesList;
	}

	public void setTopkMoviesList(ArrayList<HashMap<String, String>> topkMoviesList) {
		this.topkMoviesList = topkMoviesList;
	}
	   
	  
	
	
}

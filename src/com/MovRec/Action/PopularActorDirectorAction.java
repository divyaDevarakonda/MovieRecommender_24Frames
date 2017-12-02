package com.MovRec.Action;


import java.util.ArrayList;
import com.MovRec.DAO.HandleMovieDataDAO;

public class PopularActorDirectorAction {
	
	private String userInputk;
	private String selectedValue;
	ArrayList<String> topkMoviesInSelected = new ArrayList<String>();
	
	public ArrayList<String> getTopkMoviesInSelected() {
		return topkMoviesInSelected;
	}


	public void setTopkMoviesInSelected(ArrayList<String> topkMoviesInSelected) {
		this.topkMoviesInSelected = topkMoviesInSelected;
	}


	public String getUserInputk() {
		return userInputk;
	}


	public void setUserInputk(String userInputk) {
		this.userInputk = userInputk;
	}


	public String getSelectedValue() {
		return selectedValue;
	}


	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	public String execute() throws Exception {
		   System.out.println("im here");
		   HandleMovieDataDAO handleMoviedata = new HandleMovieDataDAO();
		   if(selectedValue!=null && selectedValue.equalsIgnoreCase("director")){
			   
			   topkMoviesInSelected =handleMoviedata.getTopPopularDirectors(Integer.parseInt(userInputk));
			   
		   }else if(selectedValue!=null && selectedValue.equalsIgnoreCase("actor")){
			   
			   topkMoviesInSelected =handleMoviedata.getTopPopularActors(Integer.parseInt(userInputk));
			   
		   }
		   
	       return "success";
	   }

}

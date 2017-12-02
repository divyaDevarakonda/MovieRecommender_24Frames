package com.MovRec.Action;

import java.util.ArrayList;

import com.MovRec.Bean.MovieDetailsBean;
import com.MovRec.DAO.HandleMovieDataDAO;

public class GenreDirectorActorTagAction {
	
	private String userInputk;
	private String selectedValue;
	private String name;
	ArrayList<MovieDetailsBean> topkMoviesInSelected = new ArrayList<MovieDetailsBean>();
	
	public ArrayList<MovieDetailsBean> getTopkMoviesInSelected() {
		return topkMoviesInSelected;
	}


	public void setTopkMoviesInSelected(ArrayList<MovieDetailsBean> topkMoviesInSelected) {
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
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String execute() throws Exception {
		   System.out.println("im here");
		   HandleMovieDataDAO handleMoviedata = new HandleMovieDataDAO();
		   if(userInputk!=null && selectedValue!=null && selectedValue.equalsIgnoreCase("genre")){
			   
			   topkMoviesInSelected =handleMoviedata.getTopkMoviesByGenre(Integer.parseInt(userInputk), name);
			   
		   }else if(selectedValue!=null && selectedValue.equalsIgnoreCase("director") && name!=null){
			   
			   topkMoviesInSelected =handleMoviedata.getTopkMoviesByDirectorName(name);
			   
		   }else if(selectedValue!=null && selectedValue.equalsIgnoreCase("actor") && name!=null){
			   
			   topkMoviesInSelected =handleMoviedata.getTopkMoviesByActorName(name);
			   
		   }else if(selectedValue!=null && selectedValue.equalsIgnoreCase("Tag")){
			   
			   topkMoviesInSelected =handleMoviedata.getTopkMoviesByTagName(name);
		   }
		   
	       return "success";
	   }

}

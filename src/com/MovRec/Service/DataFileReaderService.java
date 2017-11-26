package com.MovRec.Service;
import java.io.BufferedReader;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;


import com.MovRec.DAO.DAOUtilities;
import com.MovRec.DAO.HandleMovieDataDAO;

public class DataFileReaderService 
{
	public  ArrayList<String[]> readDataFile() throws Exception{
		ArrayList<String[]> eachFileData = readEachFile("/Users/divyadevarakonda/Documents/204/RTD/movies.dat");
		return eachFileData;
	}
	
	
	public ArrayList<String[]> readEachFile(String fileName){
		ArrayList<String[]> eachfileData = new ArrayList<String[]>();
		DAOUtilities daoUtilities = new DAOUtilities();
		try{
		FileReader fr=new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		br.readLine();
		String line = "";
		while ((line=br.readLine())!=null){
			String[] eachLine = line.split("\t");
			eachfileData.add(eachLine);
		}
	    br.close();    
        fr.close();
		}catch(Exception e){
			e.getMessage();
		}
		return eachfileData;
	}
	
	public ArrayList<String> getTopkMovies(int userInputk){
		String movieList=null;
		HandleMovieDataDAO handleMovieDataDAO = new HandleMovieDataDAO();
		//handleMovieDataDAO.getTopkMoviesbyAudianceScore(userInputk);
		
		return null;
	}
		
//	public File[] getDataFilesList(){
//		File folder = new File("/Users/divyadevarakonda/Documents/204/RTD/");
//		File[] listOfFiles = folder.listFiles();
//
//		    for (int i = 0; i < listOfFiles.length; i++) {
//		      if (listOfFiles[i].isFile()) {
//		        System.out.println("File " + listOfFiles[i].getName());
//		      }
//		    }
//		return listOfFiles;
//		
//	}
	
//	public static void main(String args[]){
//		String[]  temp = new String[4];
//		String word = " hellow	world";
//		System.out.println("length of temp is "+temp.length);
//		String[] abc=word.split("\t");
//		for(int i=0;i<abc.length;i++){
//			temp[i]=abc[i];
//		}
//		for(int i=0;i<temp.length;i++){
//			System.out.println("temp values "+temp[i]);
//		}		
//		System.out.println("length of temp is "+temp.length);
//	}
}
package com.sunny.newmovierecommendation;

import java.util.ArrayList;

public class RecommendGenre {

	private String gender;
	private ArrayList<String> genreList = new ArrayList<String>();
	
	RecommendGenre(String gender){
		this.gender = gender;
	}
	
	//method to recommend genres
	public ArrayList<String> recommendGenre(){
		
		if(this.gender.equals("male")){
			genreList.add("comedy");
		}
		else{
			genreList.add("comedy");
			genreList.add("action");
		}
			
		
		return genreList;
	}
	
	
}

package com.sunny.newmovierecommendation;

import java.util.ArrayList;

public class RecommendMovieFromGenre {

	public ArrayList<String> movieList = new ArrayList<String>();
	
	//method to recommend movie based on genre
	public ArrayList<String> recommendMoviesFromGenre(ArrayList<String> genreList){
		
		if(genreList.contains("comedy")){
			movieList.add("Hera Pheri");
		}
		return movieList;
	}
}

package com.sunny.newmovierecommendation;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MovieScreen extends ListActivity {
	
	
    //private static String newMovieFilePath = "https://docs.google.com/document/d/1H73vqvvYoi9PDRNTtPp6x-l3IbZHrX6V1yCyNbMe4lc/edit?hl=en_US";
    //private static String upcomingMovieFilePath = "https://docs.google.com/document/d/1ekJRicKyS64cpj0rt2Or5_tBjkNb2DyPUdfC9TKEIQE/edit?hl=en_US";
    private static String newMovieFilePath = "http://sunnypanjwani.com/newmovies.txt";
    private static String upcomingMovieFilePath = "http://sunnypanjwani.com/upcomingmovies.txt"; 
    static URL url1, url2;
    static private URLConnection urlConn1, urlConn2; 
    static DataInputStream  dis1, dis2;
	public static ArrayList<String> list = null;
	public static ArrayList<String> list1 = new ArrayList<String>();
    public static ArrayList<String> list2 = new ArrayList<String>();
    
    static{
    	try {
    		  String s = null; 
    		  
    		  url1 = new URL(newMovieFilePath);
			  urlConn1 = url1.openConnection(); 
			  urlConn1.setDoInput(true); 
			  urlConn1.setUseCaches(false);
			  dis1 = new DataInputStream(urlConn1.getInputStream()); 
			   
		      while ((s = dis1.readLine()) != null){
				   list1.add(s);
			  }
			    
			  url2 = new URL(upcomingMovieFilePath);
			  urlConn2 = url2.openConnection(); 
			  urlConn2.setDoInput(true); 
			  urlConn2.setUseCaches(false);
			  dis2 = new DataInputStream(urlConn2.getInputStream());
			  
			  //int j=0; 
			  while ((s = dis2.readLine()) != null){
				    list2.add(s);
		      }
			  
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
	
	
	public void onCreate(Bundle icicle){
	      super.onCreate(icicle);
	     
	      setListAdapter(new ArrayAdapter<String>(this, R.layout.list_view, list));
	      final ListView lv = getListView();
	      lv.setTextFilterEnabled(true);
	     
	}
	
	
	
	//helper function to set the value for list according to the requested service
	public static void setListValue(long id){
		
		if(id == 1){
			list = list1;
		}
		else{
			list = list2;
		}
	}
}

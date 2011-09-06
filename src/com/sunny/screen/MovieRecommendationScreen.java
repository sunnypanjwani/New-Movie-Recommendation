package com.sunny.screen;

import java.util.ArrayList;

import com.sunny.newmovierecommendation.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MovieRecommendationScreen extends ListActivity{

	ArrayList<String> list ;
	
	public void onCreate(Bundle icicle){
	      super.onCreate(icicle);
	     
	      setListAdapter(new ArrayAdapter<String>(this, R.layout.list_view, this.list));
	      final ListView lv = getListView();
	      lv.setTextFilterEnabled(true);
	     
	}
	
	//helper function to set the list
	public void setList(ArrayList<String> list){
		this.list = list;
	}
	
}

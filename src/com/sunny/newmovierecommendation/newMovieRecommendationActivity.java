package com.sunny.newmovierecommendation;

import java.io.IOException;
import java.net.MalformedURLException;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.sunny.screen.MovieRecommendationScreen;
import com.sunny.screen.MovieScreen;

public class newMovieRecommendationActivity extends ListActivity {

    Facebook facebook = new Facebook("190033754396514");
    String gender;
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
      
        //sign in using facebook  
        
      facebook.authorize(this, new DialogListener() {
            public void onComplete(Bundle values) {}

            public void onFacebookError(FacebookError error) {}

            public void onError(DialogError e) {}

            public void onCancel() {}
        });
       
      
      //fetch users info from facebook
      try {
		
    	  gender = facebook.request("gender");
    	  System.out.println("gender is :"+gender);
		
      }
      catch (MalformedURLException e1) {
		// TODO Auto-generated catch block
    	  	e1.printStackTrace();
      } 
      catch (IOException e1) {
		// TODO Auto-generated catch block
      		e1.printStackTrace();
      }    
       
       String[] buttons = getResources().getStringArray(R.array.main_buttons);
       setListAdapter(new ArrayAdapter<String>(this, R.layout.list_view, buttons));

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);

        lv.setOnItemClickListener(new OnItemClickListener() {
          
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		MovieScreen.setListValue(id);
        		
        		if(id != 0){
        			Intent i = new Intent(newMovieRecommendationActivity.this, MovieScreen.class);
        			startActivity(i);
        		}
        		else{
        			
        			RecommendGenre recGenre = new RecommendGenre(gender);
        			MovieRecommendationScreen movieRec = new MovieRecommendationScreen();
        			movieRec.setList(recGenre.recommendGenre());
        			Intent i = new Intent(newMovieRecommendationActivity.this, MovieRecommendationScreen.class);
        			startActivity(i);
        		}
        	  
             }
        });
        
    }
    
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        facebook.authorizeCallback(requestCode, resultCode, data);
    }
	
}
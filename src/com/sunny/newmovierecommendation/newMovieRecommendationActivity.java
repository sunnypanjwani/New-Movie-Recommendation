package com.sunny.newmovierecommendation;

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
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import com.sunny.screen.MovieRecommendationScreen;
import com.sunny.screen.MovieScreen;

public class newMovieRecommendationActivity extends ListActivity {

   
	
	 
	Facebook facebook = new Facebook("190033754396514");
	
	String accessToken;
    
    User user;
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
      
        //sign in using facebook  
        
      facebook.authorize(this, new String[] { "offline_access", "publish_stream", "email" }, new DialogListener() {
            
    	  
    	    public void onComplete(Bundle values) {
    	    	if(accessToken==null){
    	      		accessToken = facebook.getAccessToken();
    	      		
    	      	   System.out.println("access token: "+accessToken);
    	      	}
    	     }

            public void onFacebookError(FacebookError error) {}

            public void onError(DialogError e) {}

            public void onCancel() {}
        });
       
      
  	FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
    	  
      //fetch users info from facebook
   // get information about the currently logged in user
	  user = facebookClient.fetchObject("me", User.class);    
	  
	  System.out.println("user gender :"+user.getGender());
     
       
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
        			
        			RecommendGenre recGenre = new RecommendGenre(user.getGender());
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
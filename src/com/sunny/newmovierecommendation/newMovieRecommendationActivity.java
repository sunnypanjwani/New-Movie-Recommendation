package com.sunny.newmovierecommendation;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

public class newMovieRecommendationActivity extends Activity {

    Facebook facebook = new Facebook("190033754396514");
    private static String newMovieFilePath = "https://docs.google.com/document/d/1H73vqvvYoi9PDRNTtPp6x-l3IbZHrX6V1yCyNbMe4lc/edit?hl=en_US";
    private static String upcomingMovieFilePath = "https://docs.google.com/document/d/1ekJRicKyS64cpj0rt2Or5_tBjkNb2DyPUdfC9TKEIQE/edit?hl=en_US";
    static URL url1, url2;
    static private URLConnection urlConn1, urlConn2; 
    static DataInputStream  dis1, dis2;
    Button button1, button2;
    
    static{
    	try {
			  url1 = new URL(newMovieFilePath);
			  urlConn1 = url1.openConnection(); 
			  urlConn1.setDoInput(true); 
			  urlConn1.setUseCaches(false);
			  dis1 = new DataInputStream(urlConn1.getInputStream()); 
			    
			  url2 = new URL(upcomingMovieFilePath);
			  urlConn2 = url2.openConnection(); 
			  urlConn2.setDoInput(true); 
			  urlConn2.setUseCaches(false);
			  dis2 = new DataInputStream(urlConn2.getInputStream());   
			  
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
  
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        facebook.authorize(this, new DialogListener() {
            public void onComplete(Bundle values) {}

            public void onFacebookError(FacebookError error) {}

            public void onError(DialogError e) {}

            public void onCancel() {}
        });
        
     
       // ButtonListener newMovieButtonListener = new ButtonListener(dis1);
        //ButtonListener upcomingMovieButtonListener = new ButtonListener(dis2);
        
        // Capture our button from layout
        
        Button button1 = (Button)findViewById(R.id.newMovieButton);
        
        // Register the onClick listener with the implementation above
        button1.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				buttonHandler(dis1);
			}
        	
        });
        
        
        Button button2 = (Button)findViewById(R.id.upcomingMovieButton);
        // Register the onClick listener with the implementation above
        button2.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				buttonHandler(dis2);
			}
        	
        });
        
        
    }

   
    private void buttonHandler(DataInputStream dis){
    	
    	String s = null;
	      
	    // do something when the button is clicked
		  try {
			  while ((s = dis.readLine()) != null){
			 
		         TextView text = new TextView(this);
				  text.setText(s);
				  text.setVisibility(1);
				   
			  }
			 // dis.close();
		  } catch (IOException e) {
			  // 	TODO Auto-generated catch block
			  e.printStackTrace();
		  	}
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        facebook.authorizeCallback(requestCode, resultCode, data);
    }
	
}
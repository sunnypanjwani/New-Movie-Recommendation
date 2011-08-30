package com.sunny.newmovierecommendation;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

public class newMovieRecommendationActivity extends Activity {

    Facebook facebook = new Facebook("190033754396514");
    //private static String newMovieFilePath = "https://docs.google.com/document/d/1H73vqvvYoi9PDRNTtPp6x-l3IbZHrX6V1yCyNbMe4lc/edit?hl=en_US";
    //private static String upcomingMovieFilePath = "https://docs.google.com/document/d/1ekJRicKyS64cpj0rt2Or5_tBjkNb2DyPUdfC9TKEIQE/edit?hl=en_US";
    private static String newMovieFilePath = "http://sunnypanjwani.com/newmovies.txt";
    private static String upcomingMovieFilePath = "http://sunnypanjwani.com/upcomingmovies.txt"; 
    static URL url1, url2;
    static private URLConnection urlConn1, urlConn2; 
    static DataInputStream  dis1, dis2;
    Button button1, button2;
    private ListView lv1;
    static String[] stringArray1 = new String[20];
    static String[] stringArray2 = new String[20];
   
    
    static{
    	try {
    		  String s = null; 
    		  
    		  url1 = new URL(newMovieFilePath);
			  urlConn1 = url1.openConnection(); 
			  urlConn1.setDoInput(true); 
			  urlConn1.setUseCaches(false);
			  dis1 = new DataInputStream(urlConn1.getInputStream()); 
			  
			  int i=0; 
		      while ((s = dis1.readLine()) != null){
				   stringArray1[i] = s;
				   i++;
			  }
			    
			  url2 = new URL(upcomingMovieFilePath);
			  urlConn2 = url2.openConnection(); 
			  urlConn2.setDoInput(true); 
			  urlConn2.setUseCaches(false);
			  dis2 = new DataInputStream(urlConn2.getInputStream());
			  
			  int j=0; 
			  while ((s = dis2.readLine()) != null){
				   stringArray2[j] = s;
				   j++;
			  }
			  
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
        
       
        lv1=(ListView)findViewById(R.id.ListView01);
        // By using setAdpater method in listview we an add string array in list.
        
         lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , stringArray1));
         lv1.setTextFilterEnabled(true);
    }

   
    public void buttonHandler(View view){
    	
    	String[] stringArray;
    	
    	if(view.getId() == R.id.upcomingMovieButton){
    		stringArray = stringArray2;
    	}
    	else{
    		stringArray = stringArray1;
    	}
    	
    	

    	  

    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        facebook.authorizeCallback(requestCode, resultCode, data);
    }
	
}
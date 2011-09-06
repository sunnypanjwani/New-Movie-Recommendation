package com.sunny.newmovierecommendation;

import java.io.DataInputStream;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

public class newMovieRecommendationActivity extends ListActivity {

    Facebook facebook = new Facebook("190033754396514");
    
    static DataInputStream  dis1, dis2;
    Button button1, button2;
    
     
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
       
       
       String[] buttons = getResources().getStringArray(R.array.main_buttons);
       setListAdapter(new ArrayAdapter<String>(this, R.layout.list_view, buttons));

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);

        lv.setOnItemClickListener(new OnItemClickListener() {
          
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		MovieScreen.setListValue(id);
        		Intent i = new Intent(newMovieRecommendationActivity.this, MovieScreen.class);
        		startActivity(i);
        	  
             }
        });
        
    }
    
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        facebook.authorizeCallback(requestCode, resultCode, data);
    }
	
}
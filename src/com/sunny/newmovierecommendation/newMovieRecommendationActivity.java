package com.sunny.newmovierecommendation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.android.*;
import com.facebook.android.Facebook.*;

public class newMovieRecommendationActivity extends Activity {

    Facebook facebook = new Facebook("190033754396514");

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
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        facebook.authorizeCallback(requestCode, resultCode, data);
    }
}
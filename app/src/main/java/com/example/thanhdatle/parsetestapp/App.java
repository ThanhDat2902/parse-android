package com.example.thanhdatle.parsetestapp;

/**
 * Created by thanhdat.le on 12.12.2016.
 */

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.SaveCallback;

public class App extends Application{
    @Override public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this.getApplicationContext()).applicationId("myAppId").server("https://safe-basin-38488.herokuapp.com/parse/").build());
        ParseInstallation.getCurrentInstallation().saveInBackground();

        Parse.setLogLevel(Parse.LOG_LEVEL_VERBOSE);

        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });
    }
}

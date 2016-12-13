package com.example.thanhdatle.parsetestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.ParsePush;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    View unsubscribeButton;
    View subscribeButton;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        setContentView(R.layout.activity_main);
        List<String> subscribedChannels = ParseInstallation.getCurrentInstallation().getList("channels");
        unsubscribeButton = findViewById(R.id.unsubscribeButton);
        subscribeButton = findViewById(R.id.subscribeButton);
        text = (TextView) findViewById(R.id.text);
        Intent i = getIntent();
        if(i.getBooleanExtra("silent",false)){
            text.setText(i.getStringExtra("data"));
        }
        if(subscribedChannels.contains("News")){
            subscribeButton.setVisibility(View.GONE);
            unsubscribeButton.setVisibility(View.VISIBLE);

        }else{
            unsubscribeButton.setVisibility(View.GONE);
            subscribeButton.setVisibility(View.VISIBLE);
        }

    }

    public void subscribeToNews(View view) {
        ParsePush.subscribeInBackground("News");
        subscribeButton.setVisibility(View.GONE);
        unsubscribeButton.setVisibility(View.VISIBLE);
    }

    public void unsubscribeToNews(View view) {
        ParsePush.unsubscribeInBackground("News");
        unsubscribeButton.setVisibility(View.GONE);
        subscribeButton.setVisibility(View.VISIBLE);
    }
}

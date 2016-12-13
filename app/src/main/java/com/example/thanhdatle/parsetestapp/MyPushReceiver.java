package com.example.thanhdatle.parsetestapp;

import android.content.Context;
import android.content.Intent;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by thanhdat.le on 13.12.2016.
 */

public class MyPushReceiver extends ParsePushBroadcastReceiver {

    @Override
    protected void onPushReceive(Context context, Intent intent) {
        try {
            JSONObject pushData = new JSONObject(intent.getStringExtra("com.parse.Data"));
            if(pushData.has("silent")){
                Intent i = new Intent(context,MainActivity.class);
                i.putExtra("silent", true);
                if(pushData.has("data")){
                    i.putExtra("data", pushData.getString("data"));
                }
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }else{
                super.onPushReceive(context, intent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

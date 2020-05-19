package com.example.denemeler;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.os.Bundle;
import android.telephony.TelephonyManager;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;


public class MyPhoneReceiver extends BroadcastReceiver {
    Call call;
    ArrayList<Call> calls;
    @Override
    public void onReceive(Context context, Intent intent){

        String state=intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
            String number=intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                loadData(context);
                call.setNumber(number);
                call.setTime(Calendar.getInstance().getTime().toString());
                calls.add(call);
                saveData(context);
        }
    }


    private void saveData(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("Calls",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(calls);
        editor.putString("Calls",json);
        editor.apply();
    }

    private void loadData(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Calls",Context.MODE_PRIVATE);
        Gson gson=new Gson();
        String json=sharedPreferences.getString("Calls",null);
        Type type=new TypeToken<ArrayList<Person>>() {}.getType();
        calls=gson.fromJson(json,type);
        if(calls==null){
            calls=new ArrayList<>();
        }

    }
}

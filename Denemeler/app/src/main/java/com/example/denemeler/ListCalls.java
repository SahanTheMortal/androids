package com.example.denemeler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ListCalls extends AppCompatActivity {
    private String username;
    private String callNunber;
    ArrayList<Call> calls;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listcalls);

        Intent i=getIntent();
        username=i.getStringExtra("username");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        CallListAdapter callAdapter = new CallListAdapter(this, calls);
        recyclerView.setAdapter(callAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK){
            Intent i=new Intent(getApplicationContext(),MainPage.class);
            i.putExtra("username",username);
            startActivity(i);
        }
        return super.onKeyDown(keyCode, event);
    }

    private void loadData(){
        SharedPreferences sharedPreferences=getSharedPreferences("Calls",MODE_PRIVATE);
        Gson gson=new Gson();
        String json=sharedPreferences.getString("Calls",null);
        Type type=new TypeToken<ArrayList<Person>>() {}.getType();
        calls=gson.fromJson(json,type);
        if(calls==null){
            calls=new ArrayList<>();
        }

    }
}

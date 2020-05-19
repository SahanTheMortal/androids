package com.example.denemeler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;

import java.lang.reflect.Type;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ListUsers extends AppCompatActivity {
    private String username;
    ArrayList<Person> users;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listusers);

        Intent i=getIntent();
        username=i.getStringExtra("username");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        UserListAdapter userAdapter = new UserListAdapter(this, users);
        recyclerView.setAdapter(userAdapter);

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
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void loadData(){
        SharedPreferences sharedPreferences=getSharedPreferences("User list",MODE_PRIVATE);
        Gson gson=new Gson();
        String json=sharedPreferences.getString("UserList",null);
        Type type=new TypeToken<ArrayList<Person>>() {}.getType();
        users=gson.fromJson(json,type);
        if(users==null){
            users=new ArrayList<>();
        }

    }
}

package com.example.denemeler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ProgressBar pb;
    ProgressDialog p;
    private Button b1,b2,b3;
    private EditText username,password;

    ArrayList<Person> users;

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    int counter = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();

        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedpreferences.edit();
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        username = (EditText)findViewById(R.id.editText1);
        password = (EditText)findViewById(R.id.editText2);

        b1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if(control(username.getText().toString(),password.getText().toString())){
                    Intent mainPage = new Intent(getApplicationContext(),MyProgressBar.class);
                    mainPage.putExtra("username",username.getText().toString());
                    startActivity(mainPage);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Wrong Information",Toast.LENGTH_SHORT).show();
                    counter--;
                    if (counter == 0) {
                        finish();
                    }
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                finish();
            }
        });
        b3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent signUp = new Intent(getApplicationContext(), SignUp.class);
                startActivity(signUp);
            }
        });

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
    private boolean control(String username,String password){
        int i=0;
        while(i<users.size()) {
            if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
                return true;
            }
            else
                i++;
        }
        return false;
    }

}

package com.example.denemeler;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.lang.String;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class SignUp extends AppCompatActivity {
    Button su,back;
    private EditText etusername,etname,etsurname,etemail,etpassword;
    private String username,name,surname,email,password;
    private Person person=new Person();
    ArrayList<Person> users;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        su = (Button) findViewById(R.id.button1);
        back = (Button) findViewById(R.id.button2);
        etusername = (EditText)findViewById(R.id.editText1);
        etname = (EditText)findViewById(R.id.editText2);
        etsurname = (EditText)findViewById(R.id.editText3);
        etemail = (EditText)findViewById(R.id.editText4);
        etpassword = (EditText)findViewById(R.id.editText5);

        su.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loadData();
                person.setUsername(etusername.getText().toString());
                person.setName(etname.getText().toString());
                person.setSurname(etsurname.getText().toString());
                person.setEmail(etemail.getText().toString());
                person.setPassword(etpassword.getText().toString());

                users.add(person);
                saveData();

                Intent main = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(main);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main=new Intent (getApplicationContext(),MainActivity.class);
                startActivity(main);
                finish();
            }
        });
    }
    private void saveData(){
        SharedPreferences sharedPreferences=getSharedPreferences("User list",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(users);
        editor.putString("UserList",json);
        editor.apply();
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

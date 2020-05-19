package com.example.denemeler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Settings extends AppCompatActivity {
    private String username,newUserName;
    private Person person,delPerson;
    ArrayList<Person> users;
    EditText et_username,et_name,et_surname,et_email,et_gender,et_length,et_weigth;
    Button saveButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent i=getIntent();
        username=i.getStringExtra("username");
        loadData();
        person=settingLoad(username);

        et_username=findViewById(R.id.editText_username);
        et_name=findViewById(R.id.editText_name);
        et_surname=findViewById(R.id.editText_surname);
        et_email=findViewById(R.id.editText_email);
        et_gender=findViewById(R.id.editText_gender);
        et_length=findViewById(R.id.editText_length);
        et_weigth=findViewById(R.id.editText_weigth);

        saveButton=findViewById(R.id.button_save);

        et_username.setText(person.getUsername());
        et_name.setText(person.getName());
        et_surname.setText(person.getSurname());
        et_email.setText(person.getEmail());
        et_gender.setText(person.getGender());
        et_length.setText(person.getLength());
        et_weigth.setText(person.getWeight());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person.setUsername(et_username.getText().toString());
                person.setName(et_name.getText().toString());
                person.setSurname(et_surname.getText().toString());
                person.setEmail(et_email.getText().toString());
                person.setGender(et_gender.getText().toString());
                person.setLength(et_length.getText().toString());
                person.setWeight(et_weigth.getText().toString());

                delPerson=settingLoad(username);
                users.remove(delPerson);

                newUserName=person.getUsername();
                users.add(person);
                saveData();
                Intent mainPage=new Intent(getApplicationContext(),MainPage.class);
                mainPage.putExtra("username",newUserName);
                startActivity(mainPage);
                finish();
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

    private void saveData(){
            SharedPreferences sharedPreferences=getSharedPreferences("User list",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            Gson gson=new Gson();
            String json=gson.toJson(users);
            editor.putString("UserList",json);
            editor.apply();
    }

    private Person settingLoad(String username){
        int i=0;
        while(i<users.size()) {
            if (users.get(i).getUsername().equals(username)) {
               return users.get(i);
            }
            else
                i++;
        }
        return null;
    }
}

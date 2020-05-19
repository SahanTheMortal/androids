package com.example.denemeler;

import android.media.Image;

public class Person {

    private String name="Unknown";
    private String surname="Unknown";
    private String username="Unknown";
    private String password="Unknown";
    private String email="Unknown";
    private String gender="Unknown";
    private String length="Unknown";
    private String weigth="Unknown";
    public Person(){}
    public Person(String name,String surname,String username,String password,String email,String gender,String length, String weight){
        this.name=name;
        this.surname=surname;
        this.username=username;
        this.password=password;
        this.email=email;
        this.gender=gender;
        this.weigth=weight;
        this.length=length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWeight() {
        return weigth;
    }

    public void setWeight(String weight) {
        this.weigth = weight;
    }
}



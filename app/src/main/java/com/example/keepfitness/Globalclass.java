package com.example.keepfitness;

import android.app.Application;

public class Globalclass extends Application {
    private String name;
    private String email;
    private String Url;

    public String getEmail() { return email;}

    public void setEmail(String email) {this.email = email;}

    public String getName() { return name;}

    public void setName(String name) { this.name = name;}

    public String getUrl() { return Url;}

    public void setUrl(String Url) { this.Url = Url;}
}

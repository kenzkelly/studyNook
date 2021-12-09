package com.example.studynook;

import android.content.Context;
import android.widget.Toast;

public class User {
    private String username;
    private String password;
    private int id;

    public User(String u, String p){
        this.username = u;
        this.password = p;
    }

    public User()
    {
        username = "";
        password = "";
        id = -1;
    }

    //returns a boolean to indicate to the activity whether or not to show a toast
    public boolean changePassword(String p)
    {
        if(checkSimilarity(p, this.password)){
            this.password = p;
            return true;
        }
        else{
            return false;
        }
    }

    private boolean checkSimilarity(String newString, String oldString)
    {
        if(newString.equalsIgnoreCase(oldString)){
            return false;
        }

        return true;
    }

    //setters
    public void setId(int id)
    {
        this.id = id;
    }

    public void setPassword(String p)
    {
        if(this.password.length() == 0)
        {
            this.password = p;
        }
    }

    public void setUsername(String u)
    {
        if(this.username.length() == 0)
        {
            this.username = u;
        }
    }

    //getters
    public String getUsername()
    {
        return this.username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public int getId()
    {
        return this.id;
    }
}

package com.example.vchat.Models;

import android.widget.EditText;

public class Users {
    String profilePic,username,email,password,user_id,lastmessage,status;

    // No-argument constructor
    public Users() {
    }

    public Users(String profilePic,String username,String email, String password,String user_id,String lastmessage,String status){
        this.profilePic=profilePic;
        this.username=username;
        this.email=email;
        this.password=password;
        this.user_id=user_id;
        this.lastmessage=lastmessage;
        this.status=status;
    }

    public Users(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Users(String string, EditText txtEmail) {
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLastmessage() {
        return lastmessage;
    }

    public void setLastmessage(String lastmessage) {
        this.lastmessage = lastmessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package com.example.user;

public class User {
    String Name, Email, Password;

    public User(String Name, String Email, String Password) {
        this.Name = Name;
        this.Email = Email;
        this.Password = Password;
    }

    public User() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

} /// 자바객체
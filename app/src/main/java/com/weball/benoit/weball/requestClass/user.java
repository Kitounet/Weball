package com.weball.benoit.weball.requestClass;

/**
 * Created by benoi on 23/01/2016.
 */
public class user {
    public String email;
    public String password;
    public String fullName;
    public String birthday;
    public String photo;

    public user(String email, String password, String fullName, String birthday)
    {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.birthday = birthday;
        this.photo = "";

    }


}

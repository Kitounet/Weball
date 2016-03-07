package com.weball.benoit.weball;

import java.io.Serializable;

/**
 * Created by benoi on 07/12/2015.
 */

public class UserInfo implements Serializable{

    private String token;
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String birthday;
    private String date;
    private String message;
    private String picture;


    String getToken()
    {
        return token;
    }
    String getId() { return id; }
    String getUsername() { return username; }
    String getFirstName() { return firstName; }
    String getLastName() { return lastName; }
    String getBirthday() { return birthday; }
    String getDate() { return date; }
    String getMessage() { return message;}
    String getPicture() { return picture;};
 }

package com.weball.benoit.weball.row;

/**
 * Created by benoi on 25/03/2016.
 */
public class Message_Row {
    private String  user_id;
    private String  username;
    private String  message;
    private String  date;

    public Message_Row(String user_id, String username, String message, String date)
    {
        this.user_id = user_id;
        this.username = username;
        this.message = message;
        this.date = date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

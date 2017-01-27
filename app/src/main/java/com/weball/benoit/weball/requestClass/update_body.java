package com.weball.benoit.weball.requestClass;

/**
 * Created by benoi on 08/06/2016.
 */
public class update_body {
    public String email;
    public String fullName;
    public String birthday;
    public String country;
    public String city;

    public update_body(String email, String fullName, String birthday, String city, String country)
    {
        this.email = email;
        this.fullName = fullName;
        this.birthday = birthday;
        this.country = country;
        this.city = city;
    }
}

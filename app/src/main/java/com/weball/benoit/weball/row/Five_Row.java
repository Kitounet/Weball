package com.weball.benoit.weball.row;

/**
 * Created by benoi on 19/02/2016.
 */
public class Five_Row {
    private String  photo;
    private String          name;
    private String          city;
    private String          id;

    public Five_Row(String photo, String name, String city, String id)
    {
        this.id = id;
        this.photo = photo;
        this.name = name;
        this.city = city;
    }

    public String getPhoto() {
        return photo;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void set_id(String id)
    {
        this.id = id;
    }

    public String get_id(){ return id;}

}

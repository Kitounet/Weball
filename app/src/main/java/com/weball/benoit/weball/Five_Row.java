package com.weball.benoit.weball;

/**
 * Created by benoi on 19/02/2016.
 */
public class Five_Row {
    private FiveInfo.Photo  photo;
    private String          name;
    private String          city;

    public Five_Row(FiveInfo.Photo photo, String name, String city)
    {
        this.photo = photo;
        this.name = name;
        this.city = city;
    }

    public FiveInfo.Photo getPhoto() {
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

    public void setPhoto(FiveInfo.Photo photo) {
        this.photo = photo;
    }
}

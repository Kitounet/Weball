package com.weball.benoit.weball.row;

/**
 * Created by benoi on 09/06/2016.
 */
public class Friend_Row {
    private String  photo;
    private String          name;
    private String          id;

    public Friend_Row(String photo, String name, String id)
    {
        this.id = id;
        this.photo = photo;
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }


    public String getName() {
        return name;
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

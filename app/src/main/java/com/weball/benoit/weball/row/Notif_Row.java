package com.weball.benoit.weball.row;

/**
 * Created by benoi on 14/04/2016.
 */
public class Notif_Row {

    private String  _id;
    private String  photo;
    private String          fullname;
    private String          message;

    public Notif_Row(String photo, String fullname, String message, String _id)
    {
        this.photo = photo;
        this.fullname = fullname;
        this.message = message;
        this._id = _id;

    }

    public String getPhoto() {
        return photo;
    }

    public String getMessage() {
        return message;
    }

    public String getFullName() {
        return fullname;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFullName(String fullname) {
        this.fullname = fullname;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}

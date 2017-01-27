package com.weball.benoit.weball.requestClass;

/**
 * Created by benoi on 05/07/2016.
 */
public class SearchFriend {
    private String fullName;
    private String photo;
    private String _id;
    private String status;

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SearchFriend(String fullname, String photo, String id)
    {
        this.fullName = fullname;
        this.photo = photo;
        this._id = id;
        this.status = "INVITER";
    }

    public String getFullName() { return fullName;}
    public String getPhoto() { return photo;}
}

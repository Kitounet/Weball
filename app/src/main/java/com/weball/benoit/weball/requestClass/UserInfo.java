package com.weball.benoit.weball.requestClass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benoi on 07/12/2015.
 */

public class UserInfo implements Serializable{

    private Integer __v;
    private String token;
    private String password;
    private String email;
    private String fullName;
    private String birthday;
    private String photo;
    private String _id;
    private String date;
    private List<Object> matchs = new ArrayList<Object>();
    private List<Object> roles = new ArrayList<Object>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The __v
     */
    public Integer get__v() {
        return __v;
    }

    /**
     *
     * @param __v
     * The __v
     */
    public void set__v(Integer __v) {
        this.__v = __v;
    }


    public void setToken(String token)
    {
        this.token = token;
    }


    public String getToken()
    {
        return this.token;
    }
    /**
     *
     * @return
     * The password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     * The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     *
     * @param fullName
     * The fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     *
     * @return
     * The birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     *
     * @param birthday
     * The birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     *
     * @return
     * The photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     *
     * @param photo
     * The photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     *
     * @return
     * The _id
     */
    public String get_id() {
        return _id;
    }

    /**
     *
     * @param _id
     * The _id
     */
    public void set_id(String _id) {
        this._id = _id;
    }

    /**
     *
     * @return
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The matchs
     */
    public List<Object> getMatchs() {
        return matchs;
    }

    /**
     *
     * @param matchs
     * The matchs
     */
    public void setMatchs(List<Object> matchs) {
        this.matchs = matchs;
    }

    /**
     *
     * @return
     * The roles
     */
    public List<Object> getRoles() {
        return roles;
    }

    /**
     *
     * @param roles
     * The roles
     */
    public void setRoles(List<Object> roles) {
        this.roles = roles;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
 }

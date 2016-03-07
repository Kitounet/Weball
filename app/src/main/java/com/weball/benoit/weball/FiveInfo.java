package com.weball.benoit.weball;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benoi on 19/02/2016.
 */
public class FiveInfo implements Serializable {
    private String _id;
    private Integer siren;
    private String name;
    private Integer zipCode;
    private String city;
    private String country;
    private String address;
    private String phone;
    private String date;
    private List<Double> gps = new ArrayList<Double>();
    private Integer V;
    private List<String> managers = new ArrayList<String>();
    private Photo photo;
    private List<String> fields = new ArrayList<String>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The _id
     */
    public String get_id() {
        return _id;
    }

    /**
     * @param _id The _id
     */
    public void set_id(String _id) {
        this._id = _id;
    }

    /**
     * @return The siren
     */
    public Integer getSiren() {
        return siren;
    }

    /**
     * @param siren The siren
     */
    public void setSiren(Integer siren) {
        this.siren = siren;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The zipCode
     */
    public Integer getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode The zipCode
     */
    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return The city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return The address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return The date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return The gps
     */
    public List<Double> getGps() {
        return gps;
    }

    /**
     * @param gps The gps
     */
    public void setGps(List<Double> gps) {
        this.gps = gps;
    }

    /**
     * @return The V
     */
    public Integer getV() {
        return V;
    }

    /**
     * @param V The __v
     */
    public void setV(Integer V) {
        this.V = V;
    }

    /**
     * @return The managers
     */
    public List<String> getManagers() {
        return managers;
    }

    /**
     * @param managers The managers
     */
    public void setManagers(List<String> managers) {
        this.managers = managers;
    }

    /**
     * @return The photo
     */
    public Photo getPhoto() {
        return photo;
    }

    /**
     * @param photo The photo
     */
    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    /**
     * @return The fields
     */
    public List<String> getFields() {
        return fields;
    }

    /**
     * @param fields The fields
     */
    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public class Photo {

        private String url;
        private String x480Base64;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * @return The url
         */
        public String getUrl() {
            return url;
        }

        /**
         * @param url The url
         */
        public void setUrl(String url) {
            this.url = url;
        }

        /**
         * @return The x480Base64
         */
        public String getX480Base64() {
            return x480Base64;
        }

        /**
         * @param x480Base64 The x480Base64
         */
        public void setX480Base64(String x480Base64) {
            this.x480Base64 = x480Base64;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }
    }
}

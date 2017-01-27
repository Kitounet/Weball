package com.weball.benoit.weball.requestClass;

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
    private String photo;
    private String name;
    private Integer zipCode;
    private String city;
    private String country;
    private String address;
    private String phone;
    private List<Double> gps = new ArrayList<Double>();
    private Integer V;
    private List<String> exeptions = new ArrayList<String>();
    private List<Day> days = new ArrayList<Day>();
    private List<Field> fields = new ArrayList<Field>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The zipCode
     */
    public Integer getZipCode() {
        return zipCode;
    }

    /**
     *
     * @param zipCode
     * The zipCode
     */
    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    /**
     *
     * @return
     * The city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     * The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     * The address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     * The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     * The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     * The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     * The gps
     */
    public List<Double> getGps() {
        return gps;
    }

    /**
     *
     * @param gps
     * The gps
     */
    public void setGps(List<Double> gps) {
        this.gps = gps;
    }

    /**
     *
     * @return
     * The V
     */
    public Integer getV() {
        return V;
    }

    /**
     *
     * @param V
     * The __v
     */
    public void setV(Integer V) {
        this.V = V;
    }

    /**
     *
     * @return
     * The exeptions
     */
    public List<String> getExeptions() {
        return exeptions;
    }

    /**
     *
     * @param exeptions
     * The exeptions
     */
    public void setExeptions(List<String> exeptions) {
        this.exeptions = exeptions;
    }

    /**
     *
     * @return
     * The days
     */
    public List<Day> getDays() {
        return days;
    }

    /**
     *
     * @param days
     * The days
     */
    public void setDays(List<Day> days) {
        this.days = days;
    }

    /**
     *
     * @return
     * The fields
     */
    public List<Field> getFields() {
        return fields;
    }

    /**
     *
     * @param fields
     * The fields
     */
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public class Day {

        private Integer day;
        private List<Hour> hours = new ArrayList<Hour>();
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         *
         * @return
         * The day
         */
        public Integer getDay() {
            return day;
        }

        /**
         *
         * @param day
         * The day
         */
        public void setDay(Integer day) {
            this.day = day;
        }

        /**
         *
         * @return
         * The hours
         */
        public List<Hour> getHours() {
            return hours;
        }

        /**
         *
         * @param hours
         * The hours
         */
        public void setHours(List<Hour> hours) {
            this.hours = hours;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

    public class Field {

        private String _id;
        private Boolean available;
        private String five;
        private String name;
        private Integer V;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
         * The available
         */
        public Boolean getAvailable() {
            return available;
        }

        /**
         *
         * @param available
         * The available
         */
        public void setAvailable(Boolean available) {
            this.available = available;
        }

        /**
         *
         * @return
         * The five
         */
        public String getFive() {
            return five;
        }

        /**
         *
         * @param five
         * The five
         */
        public void setFive(String five) {
            this.five = five;
        }

        /**
         *
         * @return
         * The name
         */
        public String getName() {
            return name;
        }

        /**
         *
         * @param name
         * The name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         *
         * @return
         * The V
         */
        public Integer getV() {
            return V;
        }

        /**
         *
         * @param V
         * The __v
         */
        public void setV(Integer V) {
            this.V = V;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

    public class Hour {

        private Integer price;
        private Double from;
        private Double to;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         *
         * @return
         * The price
         */
        public Integer getPrice() {
            return price;
        }

        /**
         *
         * @param price
         * The price
         */
        public void setPrice(Integer price) {
            this.price = price;
        }

        /**
         *
         * @return
         * The from
         */
        public Double getFrom() {
            return from;
        }

        /**
         *
         * @param from
         * The from
         */
        public void setFrom(Double from) {
            this.from = from;
        }

        /**
         *
         * @return
         * The to
         */
        public Double getTo() {
            return to;
        }

        /**
         *
         * @param to
         * The to
         */
        public void setTo(Double to) {
            this.to = to;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }
}

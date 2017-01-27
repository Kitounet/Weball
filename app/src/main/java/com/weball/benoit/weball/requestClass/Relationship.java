package com.weball.benoit.weball.requestClass;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by benoi on 10/11/2016.
 */

public class Relationship implements Serializable {
    private User user;
    private String _id;
    private String date;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The user
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return
     * The _id
     */
    public String getId() {
        return _id;
    }

    /**
     *
     * @param _id
     * The _id
     */
    public void setId(String _id) {
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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public class User {

        private String _id;
        private String fullName;
        private String photo;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         *
         * @return
         * The _id
         */
        public String getId() {
            return _id;
        }

        /**
         *
         * @param _id
         * The _id
         */
        public void setId(String _id) {
            this._id = _id;
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

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

}

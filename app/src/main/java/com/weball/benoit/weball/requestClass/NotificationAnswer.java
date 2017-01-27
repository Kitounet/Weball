package com.weball.benoit.weball.requestClass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benoi on 13/06/2016.
 */
public class NotificationAnswer implements Serializable{
    private List<Notification> notifications = new ArrayList<Notification>();
    private List<Request> requests = new ArrayList<Request>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The notifications
     */
    public List<Notification> getNotifications() {
        return notifications;
    }

    /**
     *
     * @param notifications
     * The notifications
     */
    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    /**
     *
     * @return
     * The requests
     */
    public List<Request> getRequests() {
        return requests;
    }

    /**
     *
     * @param requests
     * The requests
     */
    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public class From {

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

    public class Notification {

        private String _id;
        private String type;
        private String contentId;
        private From from;
        private String date;
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
         * The type
         */
        public String getType() {
            return type;
        }

        /**
         *
         * @param type
         * The type
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         *
         * @return
         * The contentId
         */
        public String getContentId() {
            return contentId;
        }

        /**
         *
         * @param contentId
         * The contentId
         */
        public void setContentId(String contentId) {
            this.contentId = contentId;
        }

        /**
         *
         * @return
         * The from
         */
        public From getFrom() {
            return from;
        }

        /**
         *
         * @param from
         * The from
         */
        public void setFrom(From from) {
            this.from = from;
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

    }

    public class Request {

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

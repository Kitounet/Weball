package com.weball.benoit.weball.requestClass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benoi on 15/07/2016.
 */
public class MatchInfo implements Serializable{
    private String _id;
    private String name;
    private String startDate;
    private String endDate;
    private Integer maxPlayers;
    private String field;
    private Five five;
    private Integer amount;
    private String createdBy;
    private String createdWith;
    private Integer currentPlayers;
    private Integer v;
    private List<Team> teams = new ArrayList<Team>();
    private List<Object> guests = new ArrayList<Object>();
    private String createdAt;
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
     * The startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     *
     * @param startDate
     * The startDate
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     *
     * @return
     * The endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     *
     * @param endDate
     * The endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     *
     * @return
     * The maxPlayers
     */
    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    /**
     *
     * @param maxPlayers
     * The maxPlayers
     */
    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    /**
     *
     * @return
     * The field
     */
    public String getField() {
        return field;
    }

    /**
     *
     * @param field
     * The field
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     *
     * @return
     * The five
     */
    public Five getFive() {
        return five;
    }

    /**
     *
     * @param five
     * The five
     */
    public void setFive(Five five) {
        this.five = five;
    }

    /**
     *
     * @return
     * The amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     * The amount
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     *
     * @return
     * The createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     *
     * @param createdBy
     * The createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     *
     * @return
     * The createdWith
     */
    public String getCreatedWith() {
        return createdWith;
    }

    /**
     *
     * @param createdWith
     * The createdWith
     */
    public void setCreatedWith(String createdWith) {
        this.createdWith = createdWith;
    }

    /**
     *
     * @return
     * The currentPlayers
     */
    public Integer getCurrentPlayers() {
        return currentPlayers;
    }

    /**
     *
     * @param currentPlayers
     * The currentPlayers
     */
    public void setCurrentPlayers(Integer currentPlayers) {
        this.currentPlayers = currentPlayers;
    }

    /**
     *
     * @return
     * The v
     */
    public Integer getV() {
        return v;
    }

    /**
     *
     * @param v
     * The __v
     */
    public void setV(Integer v) {
        this.v = v;
    }

    /**
     *
     * @return
     * The teams
     */
    public List<Team> getTeams() {
        return teams;
    }

    /**
     *
     * @param teams
     * The teams
     */
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    /**
     *
     * @return
     * The guests
     */
    public List<Object> getGuests() {
        return guests;
    }

    /**
     *
     * @param guests
     * The guests
     */
    public void setGuests(List<Object> guests) {
        this.guests = guests;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The createdAt
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public class Five {

        private String _id;
        private String photo;
        private String name;
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

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

    public class Team {

        private String name;
        private String _id;
        private Integer buts;
        private Integer currentPlayers;
        private Boolean _private;
        private List<User> users = new ArrayList<User>();
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
         * The buts
         */
        public Integer getButs() {
            return buts;
        }

        /**
         *
         * @param buts
         * The buts
         */
        public void setButs(Integer buts) {
            this.buts = buts;
        }

        /**
         *
         * @return
         * The currentPlayers
         */
        public Integer getCurrentPlayers() {
            return currentPlayers;
        }

        /**
         *
         * @param currentPlayers
         * The currentPlayers
         */
        public void setCurrentPlayers(Integer currentPlayers) {
            this.currentPlayers = currentPlayers;
        }

        /**
         *
         * @return
         * The _private
         */
        public Boolean getPrivate() {
            return _private;
        }

        /**
         *
         * @param _private
         * The private
         */
        public void setPrivate(Boolean _private) {
            this._private = _private;
        }

        /**
         *
         * @return
         * The users
         */
        public List<User> getUsers() {
            return users;
        }

        /**
         *
         * @param users
         * The users
         */
        public void setUsers(List<User> users) {
            this.users = users;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }


    public class User
    {
        private String _id;
        private String  fullName;
        private String  photo;

        public User(String _id, String fullName, String photo) {
            this._id = _id;
            this.fullName = fullName;
            this.photo = photo;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }

}

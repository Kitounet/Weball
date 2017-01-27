package com.weball.benoit.weball.requestClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benoi on 28/03/2016.
 */
public class Matchs {
    private String _id;
    private String name;
    private String startDate;
    private String endDate;
    private Integer maxPlayers;
    private String field;
    private Integer amount;
    private CreatedBy createdBy;
    private String createdWith;
    private Integer currentPlayers;
    private Integer v;
    private List<Team> teams = new ArrayList<Team>();
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
    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    /**
     *
     * @param createdBy
     * The createdBy
     */
    public void setCreatedBy(CreatedBy createdBy) {
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

    public class Team {

        private String name;
        private Integer buts;
        private Integer currentPlayers;
        private Boolean _private;
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

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

    public class CreatedBy {

        private String _id;
        private String email;
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

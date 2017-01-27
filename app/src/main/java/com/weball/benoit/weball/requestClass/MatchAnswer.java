package com.weball.benoit.weball.requestClass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benoi on 29/03/2016.
 */
public class MatchAnswer {
    private Integer v;
    private String name;
    private String startDate;
    private String endDate;
    private Integer maxPlayers;
    private String field;
    private String five;
    private Integer amount;
    private String createdBy;
    private String createdWith;
    private Integer currentPlayers;
    private String _id;
    private List<Team> teams = null;
    private List<Guest> guests = null;
    private String createdAt;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFive() {
        return five;
    }

    public void setFive(String five) {
        this.five = five;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedWith() {
        return createdWith;
    }

    public void setCreatedWith(String createdWith) {
        this.createdWith = createdWith;
    }

    public Integer getCurrentPlayers() {
        return currentPlayers;
    }

    public void setCurrentPlayers(Integer currentPlayers) {
        this.currentPlayers = currentPlayers;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public class Guest {

        private String by;
        private String to;
        private String _id;
        private String date;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public String getBy() {
            return by;
        }

        public void setBy(String by) {
            this.by = by;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getId() {
            return _id;
        }

        public void setId(String _id) {
            this._id = _id;
        }

        public String getDate() {
            return date;
        }

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
    public class Team {

        private String name;
        private String _id;
        private Integer buts;
        private Integer currentPlayers;
        private Boolean _private;
        private List<String> users = null;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return _id;
        }

        public void setId(String _id) {
            this._id = _id;
        }

        public Integer getButs() {
            return buts;
        }

        public void setButs(Integer buts) {
            this.buts = buts;
        }

        public Integer getCurrentPlayers() {
            return currentPlayers;
        }

        public void setCurrentPlayers(Integer currentPlayers) {
            this.currentPlayers = currentPlayers;
        }

        public Boolean getPrivate() {
            return _private;
        }

        public void setPrivate(Boolean _private) {
            this._private = _private;
        }

        public List<String> getUsers() {
            return users;
        }

        public void setUsers(List<String> users) {
            this.users = users;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }
}
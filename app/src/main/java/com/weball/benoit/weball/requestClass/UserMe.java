package com.weball.benoit.weball.requestClass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benoi on 14/04/2016.
 */
public class UserMe implements Serializable {
    private String fullName;
    private String birthday;
    private String city;
    private String country;
    private String _id;
    private String photo;
    private NMatchs _nMatchs;
    private List<NextMatch> _nextMatches = new ArrayList<NextMatch>();
    private RelationShip _relationShip;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
     * The _nMatchs
     */
    public NMatchs getNMatchs() {
        return _nMatchs;
    }

    /**
     *
     * @param _nMatchs
     * The _nMatchs
     */
    public void setNMatchs(NMatchs _nMatchs) {
        this._nMatchs = _nMatchs;
    }

    /**
     *
     * @return
     * The _nextMatches
     */
    public List<NextMatch> getNextMatches() {
        return _nextMatches;
    }

    /**
     *
     * @param _nextMatches
     * The _nextMatches
     */
    public void setNextMatches(List<NextMatch> _nextMatches) {
        this._nextMatches = _nextMatches;
    }

    /**
     *
     * @return
     * The _relationShip
     */
    public RelationShip getRelationShip() {
        return _relationShip;
    }

    /**
     *
     * @param _relationShip
     * The _relationShip
     */
    public void setRelationShip(RelationShip _relationShip) {
        this._relationShip = _relationShip;
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

    public class NMatchs {

        private Integer win;
        private Integer loose;
        private Integer nul;
        private Integer total;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         *
         * @return
         * The win
         */
        public Integer getWin() {
            return win;
        }

        /**
         *
         * @param win
         * The win
         */
        public void setWin(Integer win) {
            this.win = win;
        }

        /**
         *
         * @return
         * The loose
         */
        public Integer getLoose() {
            return loose;
        }

        /**
         *
         * @param loose
         * The loose
         */
        public void setLoose(Integer loose) {
            this.loose = loose;
        }

        /**
         *
         * @return
         * The nul
         */
        public Integer getNul() {
            return nul;
        }

        /**
         *
         * @param nul
         * The nul
         */
        public void setNul(Integer nul) {
            this.nul = nul;
        }

        /**
         *
         * @return
         * The total
         */
        public Integer getTotal() {
            return total;
        }

        /**
         *
         * @param total
         * The total
         */
        public void setTotal(Integer total) {
            this.total = total;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

    public class NextMatch {

        private String _id;
        private String startDate;
        private Five five;
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

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

    public class RelationShip {

        private Integer nRelations;
        private Integer isRelation;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * @return The nRelations
         */
        public Integer getNRelations() {
            return nRelations;
        }

        /**
         * @param nRelations The nRelations
         */
        public void setNRelations(Integer nRelations) {
            this.nRelations = nRelations;
        }

        /**
         * @return The isRelation
         */
        public Integer getIsRelation() {
            return isRelation;
        }

        /**
         * @param isRelation The isRelation
         */
        public void setIsRelation(Integer isRelation) {
            this.isRelation = isRelation;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }
    }
}

package com.weball.benoit.weball.requestClass;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by benoi on 11/11/2016.
 */

public class SimpleAnswer implements Serializable {
    private Integer ok;
    private Integer nModified;
    private Integer n;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The ok
     */
    public Integer getOk() {
        return ok;
    }

    /**
     *
     * @param ok
     * The ok
     */
    public void setOk(Integer ok) {
        this.ok = ok;
    }

    /**
     *
     * @return
     * The nModified
     */
    public Integer getNModified() {
        return nModified;
    }

    /**
     *
     * @param nModified
     * The nModified
     */
    public void setNModified(Integer nModified) {
        this.nModified = nModified;
    }

    /**
     *
     * @return
     * The n
     */
    public Integer getN() {
        return n;
    }

    /**
     *
     * @param n
     * The n
     */
    public void setN(Integer n) {
        this.n = n;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}


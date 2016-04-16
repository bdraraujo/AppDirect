package com.acme.types;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;

/**
 * Created by bdraraujo on 16-04-15.
 */
@Entity
public class Event {
    private String returnUrl;

    private String flag;

    private Key<Payload> payload;

    private String type;

    private Marketplace marketplace;

    private Creator creator;

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Key<Payload> getPayload() {
        return payload;
    }

    public void setPayload(Key<Payload> payload) {
        this.payload = payload;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Marketplace getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(Marketplace marketplace) {
        this.marketplace = marketplace;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "ClassPojo [returnUrl = " + returnUrl + ", flag = " + flag + ", payload = " + payload + ", type = " + type + ", marketplace = " + marketplace + ", creator = " + creator + "]";
    }
}

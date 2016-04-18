package com.acme.types;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by bdraraujo on 16-04-15.
 */
@Entity
public class Marketplace {
    private String baseUrl;

    @Id
    private String partner;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    @Override
    public String toString() {
        return "ClassPojo [baseUrl = " + baseUrl + ", partner = " + partner + "]";
    }
}

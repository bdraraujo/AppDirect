package com.acme.types;

import com.googlecode.objectify.annotation.Entity;

/**
 * Created by bdraraujo on 16-04-15.
 */
@Entity
public class Payload {
    private Order order;

    private Company company;

    private Configuration configuration;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String toString() {
        return "ClassPojo [order = " + order + ", company = " + company + ", configuration = " + configuration + "]";
    }
}

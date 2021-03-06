package com.acme.types;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by bdraraujo on 16-04-15.
 */
@Entity
public class Payload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Order order;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Company company;

    @ManyToOne(cascade = {CascadeType.ALL})
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

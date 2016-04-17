package com.acme.types;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

/**
 * Created by bdraraujo on 16-04-15.
 */
@Entity
@Table(name = "event_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pricingDuration;

    @OrderColumn
    @OneToMany(mappedBy = "order", cascade = {CascadeType.ALL})
    private List<Item> item;

    private String editionCode;

    public String getPricingDuration() {
        return pricingDuration;
    }

    public void setPricingDuration(String pricingDuration) {
        this.pricingDuration = pricingDuration;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    public String getEditionCode() {
        return editionCode;
    }

    public void setEditionCode(String editionCode) {
        this.editionCode = editionCode;
    }

    @Override
    public String toString() {
        return "ClassPojo [pricingDuration = " + pricingDuration + ", item = " + item + ", editionCode = " + editionCode + "]";
    }
}

package com.acme.types;

import com.googlecode.objectify.annotation.Entity;

/**
 * Created by bdraraujo on 16-04-15.
 */
@Entity
public class Item {
    private String unit;

    private String quantity;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ClassPojo [unit = " + unit + ", quantity = " + quantity + "]";
    }
}

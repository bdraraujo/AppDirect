package com.acme.types;

import com.googlecode.objectify.annotation.Entity;

/**
 * Created by bdraraujo on 16-04-15.
 */
@Entity
public class Entry {
    private String value;

    private String key;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "ClassPojo [value = " + value + ", key = " + key + "]";
    }
}

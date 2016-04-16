package com.acme.types;

import com.googlecode.objectify.annotation.Entity;

/**
 * Created by bdraraujo on 16-04-15.
 */
@Entity
public class Configuration {
    private Entry entry;

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    @Override
    public String toString() {
        return "ClassPojo [entry = " + entry + "]";
    }
}

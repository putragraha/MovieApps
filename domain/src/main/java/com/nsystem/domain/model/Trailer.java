package com.nsystem.domain.model;


public class Trailer {

    private int trailerId;
    private String key;
    private String name;

    public Trailer(int trailerId) {
        this.trailerId = trailerId;
    }

    public int getTrailerId() {
        return trailerId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

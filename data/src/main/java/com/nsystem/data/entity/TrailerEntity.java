package com.nsystem.data.entity;

import com.google.gson.annotations.SerializedName;

public class TrailerEntity {

    @SerializedName("id")
    private int trailerId;
    private String key;
    private String name;

    public int getTrailerId() {
        return trailerId;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
}

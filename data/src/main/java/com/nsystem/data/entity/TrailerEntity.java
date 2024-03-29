package com.nsystem.data.entity;

import com.google.gson.annotations.SerializedName;

public class TrailerEntity {

    @SerializedName("id")
    private String trailerId;
    private String key;
    private String name;
    private String site;

    public String getTrailerId() {
        return trailerId;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }
}

package com.flexfare.android.user.LocationProvider.model;

/**
 * Created by kodenerd on 11/6/17.
 */

public class LocalGovernmentArea {

    String name;
    int id;

    public LocalGovernmentArea(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

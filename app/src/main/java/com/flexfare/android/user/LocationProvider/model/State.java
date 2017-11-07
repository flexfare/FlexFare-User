package com.flexfare.android.user.LocationProvider.model;

import org.json.JSONArray;

/**
 * Created by kodenerd on 11/6/17.
 */

public class State {

    String name;
    int id;
    JSONArray mLGAArray;

    public State(String name, int id, JSONArray lGAArray) {
        this.name = name;
        this.id = id;
        this.mLGAArray = lGAArray;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public JSONArray getmLGAArray() {
        return mLGAArray;
    }
}
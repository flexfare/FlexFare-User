package com.flexfare.android.user;

import java.io.Serializable;

/**
 * Created by Blessyn on 11/9/2017.
 */

public class DriverModel implements Serializable {
    private String user_id;
    private String driver_name;
    private String car_name;
    private String car_plate_number;
    private String image_path;
    private String car_image_path;
    private String driver_phone_number;
    private String driver_description;
    private String state_option;
    private String driver_type;
    private String State;
    private String lga;

    public DriverModel() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getCar_plate_number() {
        return car_plate_number;
    }

    public void setCar_plate_number(String car_plate_number) {
        this.car_plate_number = car_plate_number;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getCar_image_path() {
        return car_image_path;
    }

    public void setCar_image_path(String car_image_path) {
        this.car_image_path = car_image_path;
    }

    public String getDriver_phone_number() {
        return driver_phone_number;
    }

    public void setDriver_phone_number(String driver_phone_number) {
        this.driver_phone_number = driver_phone_number;
    }

    public String getDriver_description() {
        return driver_description;
    }

    public void setDriver_description(String driver_description) {
        this.driver_description = driver_description;
    }

    public String getState_option() {
        return state_option;
    }

    public void setState_option(String state_option) {
        this.state_option = state_option;
    }

    public String getDriver_type() {
        return driver_type;
    }

    public void setDriver_type(String driver_type) {
        this.driver_type = driver_type;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getLga() {
        return lga;
    }

    public void setLga(String lga) {
        this.lga = lga;
    }
}

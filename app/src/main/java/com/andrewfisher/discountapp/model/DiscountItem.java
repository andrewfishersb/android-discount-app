package com.andrewfisher.discountapp.model;

import java.io.Serializable;

/**
 * Created by andrewfisher on 1/11/17.
 */
public class DiscountItem implements Serializable{
     String id;
     String store;
     String address;
     String city;
     String state;
     String zipcode;
     String phone;
     String discount;
     int categoryId;
     double latitude;
     double longitude;
     String miles;

    public DiscountItem(String id, String store, String address, String city, String state, String zip, String phone,
                        String discount, int categoryId, double latitude, double longitude, String miles) {
        this.id = id;
        this.store = store;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zip;
        this.phone = phone;
        this.discount = discount;
        this.categoryId = categoryId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.miles = miles;
    }

    public String getId() {
        return id;
    }

    public String getStore() {
        return store;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getPhone() {
        return phone;
    }

    public String getDiscount() {
        return discount;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getMiles() {
        return miles;
    }
}

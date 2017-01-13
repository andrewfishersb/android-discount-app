package com.andrewfisher.discountapp.model;

import java.io.Serializable;

//implements serializable so the object can easily be passed through intents, and Comparable so it is easy to compare with other objects and easy to sort by category id
public class DiscountItem implements Serializable, Comparable<DiscountItem>{
    private String id;
    private String store;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String phone;
    private String discount;
    private int categoryId;
    private double latitude;
    private double longitude;
    private String miles;

    //One huge constructor
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

    //Rewritten in order to sort the list of objects by category id, when using Collections.sort
    @Override
    public int compareTo(DiscountItem otherItem){
        if(categoryId >otherItem.categoryId){
            return 1;
        }else if (categoryId < otherItem.categoryId){
            return -1;
        }
        return 0;
    }

    //Getters
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

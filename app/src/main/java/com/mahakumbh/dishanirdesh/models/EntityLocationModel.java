package com.mahakumbh.dishanirdesh.models;


import com.google.android.gms.maps.model.LatLng;

public class EntityLocationModel {
    long id;

    String category; //ENUM
    String title;
    String description;
    double latitude ;
    double longitude ;
    int menu; //can be null
    int image;
    String address;
    String mobileNumber;

    String tags;

    public EntityLocationModel() {
    }

    public EntityLocationModel(String category, String title, String description, double latitude, double longitude, int menu, int image, String address, String mobileNumber, String tags) {
        this.category = category;
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.menu = menu;
        this.image = image;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.tags = tags;
    }

    public EntityLocationModel(long id, String category, String title, String description, double latitude, double longitude, int menu, int image, String address, String mobileNumber, String tags) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.menu = menu;
        this.image = image;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.tags = tags;
    }

    public EntityLocationModel(String category, String title, String description, double latitude, double longitude, int menu, int image) {
        this.category = category;
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.menu = menu;
        this.image = image;
    }

    public EntityLocationModel(int i, String title, String description, double latitude, double longitude, String tags) {
        this.id = i;
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tags = tags;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public LatLng getLocation(){

        return new LatLng(latitude,longitude);

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
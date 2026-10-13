package com.piyus.JavaHibernatePractical.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String houseNo;
    private String city;
    private String street;
    private String pincode;
    private String state;

    public Address(String houseNo,
                   String city,
                   String street,
                   String pincode,
                   String state
    ) {
        this.houseNo =  houseNo;
        this.city = city;
        this.street = street;
        this.pincode = pincode;
        this.state = state;
    }

    public Address(){}

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

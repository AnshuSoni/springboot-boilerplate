package com.example.demo.models;

import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private EAddressType addressType;
    private String streetName;
    private String secondStreet;
    private String landmark;
    private String pinCode;
    private String phoneNumber;

    @OneToOne
    private Users user;

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getSecondStreet() {
        return secondStreet;
    }

    public void setSecondStreet(String secondStreet) {
        this.secondStreet = secondStreet;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public EAddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(EAddressType addressType) {
        this.addressType = addressType;
    }
    // Address Builder

    private Address(Builder builder) {
        this.setPhoneNumber(builder.phoneNumber);
        this.setName(builder.name);
        this.setAddressType(builder.addressType);
        this.setLandmark(builder.landmark);
        this.setStreetName(builder.streetName);
        this.setSecondStreet(builder.secondStreet);
        this.setPinCode(builder.pinCode);
    }

    public static class Builder {

        // Mandatory Fields
        private String name;
        private EAddressType addressType;
        private String pinCode;
        private String phoneNumber;

        // Non Mandatory Fields
        private String streetName;
        private String secondStreet;
        private String landmark;

        public Builder(String name, EAddressType addressType, String pinCode, String phoneNumber) {
            this.name = name;
            this.addressType = addressType;
            this.pinCode = pinCode;
            this.phoneNumber = phoneNumber;
        }

        public Builder streetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public Builder secondStreet(String secondStreet) {
            this.secondStreet = secondStreet;
            return this;
        }

        public Builder landmark(String landmark) {
            this.landmark = landmark;
            return this;
        }

        public Address build(){
            return new Address(this);
        }

    }


}

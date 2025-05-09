package com.example.demo.dto;

import com.example.demo.models.EAddressType;
import com.example.demo.models.ERole;
import com.example.demo.validations.PasswordMatch;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;


@PasswordMatch
public class SignupRequest {
    // mandatory fields

    @NotNull
    private String name;

    @NotNull
    private String email;
    private String username;

    @NotNull
    @Min(8)
    private String password;

    @NotNull
    @Min(8)
    private String confirmPassword;

    // optional fields
    private Integer pincode;
    private String streetName;
    private String landmark;
    private String secondStreet;
    private String phoneNumber;
    private EAddressType addressType;


    // defaults to user role
    private List<ERole> roles;

    public SignupRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getSecondStreet() {
        return secondStreet;
    }

    public void setSecondStreet(String secondStreet) {
        this.secondStreet = secondStreet;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public EAddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(EAddressType addressType) {
        this.addressType = addressType;
    }

    public List<ERole> getRoles() {
        return roles;
    }

    public void setRoles(List<ERole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "SignupRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", pincode=" + pincode +
                ", streetName='" + streetName + '\'' +
                ", landmark='" + landmark + '\'' +
                ", secondStreet='" + secondStreet + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", addressType=" + addressType +
                ", roles=" + roles +
                '}';
    }
}

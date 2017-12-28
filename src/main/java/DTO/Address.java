/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author Aaron
 */
public class Address {
    private int addressId;
    private int userId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String county_state;
    private String country;

    public Address() {
        addressId=-1;
        userId=-1;
        addressLine1 = null;
        addressLine2 =null;
        city = null;
        county_state=null;
        country=null;
        
        
    }

    public Address(int addressId, int userId, String addressLine1, String addressLine2, String city, String county_state, String country) {
        this.addressId = addressId;
        this.userId = userId;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.county_state = county_state;
        this.country = country;
    }

    public Address(int userId, String addressLine1, String addressLine2, String city, String county_state, String country) {
        this.userId = userId;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.county_state = county_state;
        this.country = country;
    }
    
    

    public int getAddressId() {
        return addressId;
    }

    public int getUserId() {
        return userId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getCounty_state() {
        return county_state;
    }

    public String getCountry() {
        return country;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCounty_state(String county_state) {
        this.county_state = county_state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.addressId;
        hash = 59 * hash + Objects.hashCode(this.addressLine1);
        hash = 59 * hash + Objects.hashCode(this.addressLine2);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (this.addressId != other.addressId) {
            return false;
        }
        if (!Objects.equals(this.addressLine1, other.addressLine1)) {
            return false;
        }
        if (!Objects.equals(this.addressLine2, other.addressLine2)) {
            return false;
        }
        return true;
    }
    

    @Override
    public String toString() {
        return "addressId=" + addressId + ", userId=" + userId + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city + ", county_state=" + county_state + ", county=" + country;
    }
 
}

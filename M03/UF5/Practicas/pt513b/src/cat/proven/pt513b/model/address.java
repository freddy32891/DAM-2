/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.pt513b.model;

import java.util.Objects;

/**
 *
 * @author Marc Bernaola Bruach
 */
public class Address {
    private String streetName;
    private String zipCode;
    private int streetNumber;
    //getters y setters
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }
    //default constructor
    public Address(){
        
    }
    //zipCode constructor
    public Address(String zipCode){
        this.zipCode = zipCode;
    }
    //full constructor
    public Address(String streetName, String zipCode, int streetNumber) {
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.streetNumber = streetNumber;
    }
    //copy constructor
    public Address(Address other ) {
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.streetNumber = streetNumber;
    }
    //toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Address: (");
        sb.append("[Street Name="); sb.append(streetName); sb.append("]");
        sb.append("[Zip Code="); sb.append(zipCode); sb.append("]");
        sb.append("[Street Number="); sb.append(streetNumber); sb.append("]");
        sb.append(")");
        return sb.toString();
    }
    //equals and hashCode
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.zipCode);
        return hash;
    }
    //equals
    @Override
    public boolean equals(Object obj) {
        boolean b = false;
        if (obj == null) { // null object
            b = false;
        } else {
            if (this == obj) {
                b = true; //the same object.
            } else {
                if (obj instanceof Employee) { //the same class.
                    Address other = (Address) obj; //convert to Address.
                    if (this.zipCode.equals(other.zipCode)) { //same zipCode
                        b = true;
                    } else {
                        b = false;
                    }
                } else {
                    b = false;
                }
            }
        }
        return b;
    }
    
}

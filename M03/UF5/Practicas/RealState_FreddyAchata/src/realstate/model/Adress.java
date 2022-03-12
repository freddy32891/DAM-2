/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realstate.model;

import java.util.Objects;

/**
 *
 * @author mati
 */
public class Adress {
 private String city;
 private String street;
 private int number;
 private int zipcode;

    public Adress() {
    }

    public Adress(String city, String street, int number, int zipcode) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.zipcode = zipcode;
    }
    public Adress(Adress other) {
        this.city = other.city;
        this.street = other.street;
        this.number = other.number;
        this.zipcode = other.zipcode;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
 
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("City= (");
        sb.append(city);
        sb.append(") ");
        sb.append("Street= (");
        sb.append(street);
        sb.append(") ");
        sb.append("Number= (");
        sb.append(number);
        sb.append("Zipcode= (");
        sb.append(zipcode);
        sb.append(") ");
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (this == obj)//if they are the same object
        {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.city);
        hash = 53 * hash + Objects.hashCode(this.street);
        hash = 53 * hash + this.number;
        hash = 53 * hash + this.zipcode;
        return hash;
    }


    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.agenda.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author fredd
 */
public class Contact implements Serializable{
    //attributes
    private String name;
    private String phone;
    private String mail;
    private String dateOfBirth;
    private String location;
    private String postalCode;

    //cosntructors
    public Contact() {
    }

    public Contact(String phone) {
        this.phone = phone;
    }

    public Contact(String name, String phone, String mail, String dateOfBirth, String location, String postalCode) {
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.dateOfBirth = dateOfBirth;
        this.location = location;
        this.postalCode = postalCode;
    }

    //setter and getter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    //equals and hash code
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.phone);
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
        final Contact other = (Contact) obj;
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        return true;
    }


    //to String
    @Override
    public String toString() {
        return "Contact{" + "name=" + name + ", phone=" + phone + ", mail=" + mail + ", dateOfBirth=" + dateOfBirth + ", location=" + location + ", postalCode=" + postalCode + '}';
    }
    
    
    
}

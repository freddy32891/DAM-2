/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.flights.model;


/**
 * ADT for a passenger
 * @author freddy
 */
public class Passenger {
    private long id;
    private String name;
    private String phone;
    private boolean minor;

    

    public Passenger() {
    }

    public Passenger(long id, String name, String phone, boolean adult) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.minor = adult;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return phone;
    }

    public void setTelephone(String phone) {
        this.phone = phone;
    }

    public boolean isMinor() {
        return minor;
    }

    public void setMinor(boolean minor) {
        this.minor = minor;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Passenger other = (Passenger) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Passenger{" + "id=" + id + ", name=" + name + ", phone=" + phone + ", minor=" + minor + '}';
    }

    
    
    
}

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
public class Estate {
    private String id;
    private String type;
    private double surface;
    private String condition;
    private boolean certificate;
    private Adress adress;
    private double price;

    public Estate(String id) {
        this.id = id;
    }

    public Estate() {
    }

    public Estate(String id, String type, double surface, String condition, boolean certificate, Adress adress, double price) {
        this.id = id;
        this.type=type;
        this.surface = surface;
        this.condition = condition;
        this.certificate = certificate;
        this.adress = adress;
        this.price = price;
    }
    public Estate(Estate other) {
        this.id = other.id;
        this.type=other.type;
        this.surface = other.surface;
        this.condition = other.condition;
        this.certificate = other.certificate;
        this.adress = other.adress;
        this.price = other.price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public boolean isCertificate() {
        return certificate;
    }

    public void setCertificate(boolean certificate) {
        this.certificate = certificate;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Estate{" + "id=" + id + ", type=" + type + ", surface=" + surface + ", condition=" + condition + ", certificate=" + certificate + ", adress=" + adress + ", price=" + price + '}';
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

      @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (this == obj)//if they are the same object
        {
            result = true;
        } else {
            if (obj == null) //if the object passed is null
            {
                result = false;
            } else {
               Estate other = (Estate) obj; //type cast
                result = this.id.equalsIgnoreCase(other.id);
            }
        }
        return result;
    }
    
}

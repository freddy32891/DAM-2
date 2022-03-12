/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.currencyconverter.model;

import java.util.Objects;

/**
 *
 * @author mati
 */
public class Coin {
    private String type;
    private double value;
    
    public Coin(){
    
    }

    public Coin(String type, double value) {
        this.type = type;
        this.value = value;
    }
    public Coin(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.type);
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
        final Coin other = (Coin) obj;
        if (!other.type.equalsIgnoreCase(other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Coin{" + "type=" + type + ", value=" + value + '}';
    }
    
    

    
    
}

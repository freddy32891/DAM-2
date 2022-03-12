/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realstate.model;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author mati
 */
public class RealEstate {
    private String name;
    private List <Estate> estates;

    public RealEstate(String name, List<Estate> estates) {
        this.name = name;
        this.estates = estates;
    }
    
    public RealEstate() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Estate> getEstates() {
        return estates;
    }

    public void setEstates(List<Estate> estates) {
        this.estates = estates;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
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
                RealEstate other = (RealEstate) obj; //type cast
                result = this.name.equalsIgnoreCase(other.name);
            }
        }
        return result;
    }

    
}

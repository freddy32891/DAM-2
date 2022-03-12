/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.storeap.model;

import java.util.Objects;

/**
 *ADT (abstract data type)
 * @author FreddySoft
 */
public class Product {

    //atributes
   private String id;
   private String description;
   private double price;
   private int stock;
   
   //constructors

    public Product(String code, String description, double price, int stock) {
        this.id = code;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    //empty constructor
    public Product(){
    
    }
    //constructor with the id as a paramethe
    public Product(String code){
        this.id=code;
    }
    //constructor with an other Product as a parameter to copy it
    public Product (Product other){
        this.id = other.id;
        this.description = other.description;
        this.price = other.price;
        this.stock = other.stock;
    }
    
    //acessors


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Code= ("); sb.append(id); sb.append(") ");
        sb.append("Description= ("); sb.append(description); sb.append(") ");
        sb.append("Price= ("); sb.append(price); sb.append(") ");
        sb.append("Stock= ("); sb.append(stock); sb.append(") ");
        
    return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result =false;
        if(this ==obj)//if they are the same object
            result=true;
        else {
        if (obj== null) //if the object passed is null
            result =false;
        else{
        if(obj instanceof Product){
        Product other = (Product)obj; //type cast
        result=this.id.equals(other.id);
        }
        }
        }
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    
    
}

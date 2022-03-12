package cat.proven.storedb.model;

import java.util.Objects;

/**
 *ADT (abstract data type)
 * @author FreddySoft
 */
public class Product {

    //atributes
   protected int id;
   protected String code;
   protected String description;
   protected double price;
   protected int stock;
   
   //constructors

    public Product(int id,String code, String description, double price, int stock) {
        this.id=id;
        this.code = code;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
    //empty constructor
    public Product(){
    
    }
    //constructor with the code as a paramethe
    public Product(String code){
        this.code=code;
    }
    //constructor with an other Product as a parameter to copy it
    public Product (Product other){
        this.id=other.id;
        this.code = other.code;
        this.description = other.description;
        this.price = other.price;
        this.stock = other.stock;
    }
    
    

    //acessors
    public int getId() {
        return id;
    }
    
    public void setId(int id) {    
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id= ("); sb.append(id); sb.append(") ");
        sb.append("Code= ("); sb.append(code); sb.append(") ");
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
        result=this.id==other.id;
        }
        }
        }
        return result;
    }

    
    
}

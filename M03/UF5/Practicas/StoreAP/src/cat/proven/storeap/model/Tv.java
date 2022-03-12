/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.storeap.model;

/**
 *
 * @author mati
 */
public class Tv extends Product {
    //attreibutes
    private int inches;
    //constructors
    public Tv(String code, String description, double price, int stock, int inches) {
        super(code, description, price, stock);
        this.inches = inches;
    }
    
    public Tv() {
    }
    
    public Tv (String code){
        super(code);
    }
    
    public Tv(Tv other){
        super(other);
        this.inches=inches;
    }
    
    //accesors
    public int getInches() {
        return inches;
    }

    public void setInches(int inches) {
        this.inches = inches;
    }
    //methods

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tv {");
        sb.append(super.toString());
        sb.append("Inches= ("); sb.append(inches); sb.append(") }");
        
    return sb.toString();
    }
    



}

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
public class Microwave extends Product {
    private int power;
    private int capacity;
    private boolean grill;

    public Microwave( String code, String description, double price, int stock, int power, int capacity, boolean grill) {
        super(code, description, price, stock);
        this.power = power;
        this.capacity = capacity;
        this.grill = grill;
    }

    public Microwave() {

    }

    public Microwave(String code) {
        super(code);
    }

    public Microwave( Microwave other) {
        super(other);
        power = other.power;
        capacity = other.capacity;
        grill = other.grill;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isGrill() {
        return grill;
    }

    public void setGrill(boolean grill) {
        this.grill = grill;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Microwave {");
        sb.append(super.toString());
        sb.append("Power= ("); sb.append(power); sb.append(") ");
        sb.append("Capacity= ("); sb.append(capacity); sb.append(") ");
        sb.append("Grill= ("); sb.append(grill); sb.append(") }");
    return sb.toString();
    }
    
    
    
}

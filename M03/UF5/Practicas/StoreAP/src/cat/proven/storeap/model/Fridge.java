/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.storeap.model;

public class Fridge extends Product {
    private int capacity;
    private boolean noFrost;

    public Fridge(String code, String description, double price, int stock, int capacity, boolean noFrost) {
        super(code, description, price, stock);
        this.capacity = capacity;
        this.noFrost = noFrost;
    }

    public Fridge() {
    }

    public Fridge( String code) {
        super(code);
    }

    public Fridge( Fridge other) {
        super(other);
        capacity = other.capacity;
        noFrost = other.noFrost;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isNoFrost() {
        return noFrost;
    }

    public void setNoFrost(boolean noFrost) {
        this.noFrost = noFrost;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fridge {");
        sb.append(super.toString());
        sb.append("Capacity= ("); sb.append(capacity); sb.append(") ");
        sb.append("noFrost= ("); sb.append(noFrost); sb.append(") }");
    return sb.toString();
    }
}

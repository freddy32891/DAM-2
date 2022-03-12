package cat.proven.customers.model;

/**
 *
 * @author ProvenSoft
 */
public class Customer {
    private long id;
    private String name;
    private String phone;
    private int yearDischarge;

    public Customer() {
    }

    public Customer(long id, String name, String phone, int yearDischarge) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.yearDischarge = yearDischarge;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getYearDischarge() {
        return yearDischarge;
    }

    public void setYearDischarge(int yearDischarge) {
        this.yearDischarge = yearDischarge;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Customer other = (Customer) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + ", phone=" + phone + ", yearDischarge=" + yearDischarge + '}';
    }
    
    
    
    
}

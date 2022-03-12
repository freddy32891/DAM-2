/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.employeeapp.model;

import com.sun.jndi.cosnaming.IiopUrl.Address;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author fredd
 */
public class Employee {
    private String phone;
    private String name;
    private int age;
    private boolean senior;
    private double salary;
    private Address  address;

    public Employee() {
    }

    public Employee(String phone, String name, int age, boolean senior, double salary, Address address) {
        this.phone = phone;
        this.name = name;
        this.age = age;
        this.senior = senior;
        this.salary = salary;
        this.address = address;
    }

    public Employee(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSenior() {
        return senior;
    }

    public void setSenior(boolean senior) {
        this.senior = senior;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" + "phone=" + phone + ", name=" + name + ", age=" + age + ", senior=" + senior + ", salary=" + salary + ", address=" + address + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.phone);
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
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        return true;
    }
    public void loadTestData(){
        Employee p= new Employee();
        List<Employee> employee=new ArrayList<>();
        
    }
    
   
    
    
    
    
}

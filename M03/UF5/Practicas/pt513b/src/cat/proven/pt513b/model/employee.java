/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.pt513b.model;

import java.util.Objects;

/**
 *
 * @author Marc Bernaola Bruach
 */
public class Employee {
    private String name;
    private String phone;
    private int age;
    private boolean senior;
    private double salary;
    private Address address;
    //getters y setters
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
    //default constructor
    public Employee() {
     
    }
    //phone constructor
    public Employee(String phone) {
        this.phone = phone;
    }
    //full constructor
    public Employee(String name, String phone, int age, boolean senior, double salary, Address address) {
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.senior = senior;
        this.salary = salary;
        this.address = address;
    }
    //copy constructor
    public Employee(Employee other){
        this.name = other.name;
        this.phone = other.phone;
        this.age = other.age;
        this.senior = other.senior;
        this.salary = other.salary;
        this.address = other.address;
    }
    //toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Employee(");
        sb.append("[Name="); sb.append(name); sb.append("]");
        sb.append("[Phone="); sb.append(phone); sb.append("]");
        sb.append("[Age="); sb.append(age); sb.append("]");
        sb.append("[Senior="); sb.append(senior); sb.append("]");
        sb.append("[Salary="); sb.append(salary); sb.append("]");
        sb.append("[Address="); sb.append(address); sb.append("]");
        sb.append(")");
        return sb.toString();
    }
    //hashcode and equals
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.phone);
        return hash;
    }
    //equals
    @Override
    public boolean equals(Object obj) {
        boolean b = false;
        if (obj == null) { // null object
            b = false;
        } else {
            if (this == obj) {
                b = true; //the same object.
            } else {
                if (obj instanceof Employee) { //the same class.
                    Employee other = (Employee) obj; //convert to Employee.
                    if (this.phone.equals(phone)) { //same phone
                        b = true;
                    } else {
                        b = false;
                    }
                } else {
                    b = false;
                }
            }
        }
        return b;
    }

}

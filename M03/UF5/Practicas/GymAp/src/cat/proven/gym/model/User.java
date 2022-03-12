/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.gym.model;

import java.util.Objects;

/**
 *
 * @author FreddySoft
 */
public class User {

    //TODO attributes
    //atributes
    private String id;
    private String name;
    private String mail;

    //constructors
    public User(String id, String name, String mail) {
        this.id = id;
        this.name = name;
        this.mail = mail;
    }

    public User(String id) {
        this.id = id;
    }

    public User() {
    }

    //accesors
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    //toString method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID= (");
        sb.append(id);
        sb.append(") ");
        sb.append("Name= (");
        sb.append(name);
        sb.append(") ");
        sb.append("Mail= (");
        sb.append(mail);
        sb.append(") ");

        return sb.toString();
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
                User other = (User) obj; //type cast
                result = this.id.equalsIgnoreCase(other.id);
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

}

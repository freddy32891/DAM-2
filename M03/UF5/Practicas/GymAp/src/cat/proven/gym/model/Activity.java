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
public class Activity {
    //TODO attributes of activity
    
    private String name;
    private String hour;
    private String weekDay;
    private int duration;
    private int capacity;
    
    //constructors
    public Activity(String name, String hour, String weekDay, int duration, int capacity) {
        this.name = name;
        this.hour = hour;
        this.weekDay = weekDay;
        this.duration = duration;
        this.capacity = capacity;
        
    }
    public Activity(){
    }

    public Activity(String name){
    this.name=name;
    }
    
    public Activity(Activity other){
    this.name=other.name;
    this.duration=other.duration;
    this.capacity=other.capacity;
    this.hour=other.hour;
    this.weekDay=other.weekDay;
    }
    
    //accesors

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    
    
    //toString Method
    
     @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name= ("); sb.append(name); sb.append(") ");
        sb.append("Day= ("); sb.append(weekDay); sb.append(") ");
        sb.append("Hour= ("); sb.append(hour); sb.append(") ");
        sb.append("Duration= ("); sb.append(duration); sb.append(") ");
        sb.append("Capacity= ("); sb.append(capacity); sb.append(") ");
        
    return sb.toString();
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
        Activity other = (Activity)obj; //type cast
        result=this.name.equalsIgnoreCase(other.name);
        }
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.name);
        return hash;
    }
    
}

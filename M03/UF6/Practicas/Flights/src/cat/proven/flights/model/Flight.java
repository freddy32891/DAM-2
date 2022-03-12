
package cat.proven.flights.model;

import java.sql.Time;
import java.util.Date;

/**
 * ADT for flight.
 * @author freddy
 */
public class Flight {
    private long id;
    private String code;
    private int capacity;
    private Date date;
    private Date time;

    public Flight() {
    }

    public Flight(long id, String code, int capacity, Date date, Date time) {
        this.id = id;
        this.code = code;
        this.capacity = capacity;
        this.date = date;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Flight other = (Flight) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Flight{" + "id=" + id + ", code=" + code + ", capacity=" + capacity + ", date=" + date + ", time=" + time + '}';
    }

    
    
}

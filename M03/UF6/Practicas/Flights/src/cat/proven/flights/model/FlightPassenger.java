package cat.proven.flights.model;

/**
 * Transport persistence class for flight-passenger mxn relationship.
 * @author ProvenSoft
 */
public class FlightPassenger {
    private long flightId;
    private long passengerId;

    public FlightPassenger(long flightId, long passengerId) {
        this.flightId = flightId;
        this.passengerId = passengerId;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(long passengerId) {
        this.passengerId = passengerId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (int) (this.flightId ^ (this.flightId >>> 32));
        hash = 37 * hash + (int) (this.passengerId ^ (this.passengerId >>> 32));
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
        final FlightPassenger other = (FlightPassenger) obj;
        if (this.flightId != other.flightId) {
            return false;
        }
        if (this.passengerId != other.passengerId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FlightPassenger{flightId=").append(flightId);
        sb.append(", passengerId=").append(passengerId);
        sb.append('}');
        return sb.toString();
    }
    
}
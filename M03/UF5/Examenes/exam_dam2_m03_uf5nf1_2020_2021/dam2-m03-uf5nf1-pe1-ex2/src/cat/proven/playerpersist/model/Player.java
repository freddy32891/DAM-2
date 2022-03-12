
package cat.proven.playerpersist.model;
import java.util.Objects;



/**
 *
 * @author freddy
 */
public class Player {
    //Attributes
    private String id;
    private String name;
    private int level;
    private double statistics;
    
    //Constructors

    public Player() {
    }

    public Player(String id, String name, int level, double statistics) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.statistics = statistics;
    }

    public Player(String id) {
        this.id = id;
    }

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getStatistics() {
        return statistics;
    }

    public void setStatistics(double statistics) {
        this.statistics = statistics;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID= (");
        sb.append(id);
        sb.append(") ");
        sb.append("Name= (");
        sb.append(name);
        sb.append(") ");
        sb.append("Level= (");
        sb.append(level);
        sb.append(") ");
        sb.append("Statistics= (");
        sb.append(statistics);
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
                Player other = (Player) obj; //type cast
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

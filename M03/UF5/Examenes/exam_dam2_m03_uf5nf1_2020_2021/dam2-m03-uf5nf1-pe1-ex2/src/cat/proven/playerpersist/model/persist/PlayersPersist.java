
package cat.proven.playerpersist.model.persist;

import cat.proven.playerpersist.model.Player;
import java.io.File;
import java.util.List;

/**
 *
 * @author 
 */
public interface PlayersPersist {
    
    /**
     * TODO
     * @param file
     * @param data
     * @return
     */
    public boolean write(File file, List<Player> data);
    
    /**
     * TODO
     * @param fila
     * @return
     */
    public List<Player> read(File file); 
    
    
}

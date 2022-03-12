package cat.proven.testparserplayer.model.persist;

import cat.proven.testparserplayer.model.Player;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author
 */
public class SAXParserPlayer {

    /**
     * Method that reads the xml file
     * @param file xml to read
     * @return an array list with information
     * @throws java.io.FileNotFoundException incase that the file given is not founded
     */
    public List<Player> load(File file)throws FileNotFoundException{
        List<Player> result = null;

        try {
            SAXParserFactory spfac = SAXParserFactory.newInstance();
            SAXParser sp = spfac.newSAXParser();
            PlayerContentHandler handler = new PlayerContentHandler();
            sp.parse(file, handler);
            result = handler.getPlayers();
        }
        catch (ParserConfigurationException ex) {
            System.out.println(ex.getMessage());
        } catch (SAXException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }

}

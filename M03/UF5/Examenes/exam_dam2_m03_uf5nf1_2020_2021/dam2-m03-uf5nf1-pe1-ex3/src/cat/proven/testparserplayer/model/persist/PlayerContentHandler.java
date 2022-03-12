package cat.proven.testparserplayer.model.persist;

import cat.proven.testparserplayer.model.Player;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author 
 */
public class PlayerContentHandler extends DefaultHandler {
    
    private String temp;
    private Player player;
    private List<Player> players;
    
    
    public List<Player> getPlayers(){
    return players;
    }



    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        temp="";
        if(qName.equalsIgnoreCase("players")){
        players=new ArrayList<>();
        }
        else if(qName.equalsIgnoreCase("player")){
        player=new Player();
        player.setId(atts.getValue("id"));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equalsIgnoreCase("players")){
        players.add(player);
        }
        else if(qName.equalsIgnoreCase("level")){
        player.setLevel(Integer.parseInt(temp));
        }else if(qName.equalsIgnoreCase("name")){
        player.setName(temp);
        }else if(qName.equalsIgnoreCase("statistics")){
        player.setStatistics(Double.parseDouble(temp));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        temp = new String(ch, start, length);
    }

    
    
}

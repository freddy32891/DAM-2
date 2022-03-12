package cat.proven.testparserplayer;

import cat.proven.testparserplayer.model.Player;
import cat.proven.testparserplayer.model.persist.SAXParserPlayer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author
 */
public class TestXMLParserPlayer {

    SAXParserPlayer sax = new SAXParserPlayer();
    List<Player> players;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestXMLParserPlayer main = new TestXMLParserPlayer();
        main.run();
        // TODO code application logic here
        // Get file name
        // Convert file data to a list of players.
        // Show player's list.
    }

    private void run() {
        
        try {
            File file = readFile();
            players=sax.load(file);
            showPlayersList(players);
        } catch (FileNotFoundException ex) {
            System.out.println("The file was not founded");
        }
    }

    private File readFile() {
        File file = null;
        String path = readString("Input the path where is the XML document to read");
        file = new File(path);
        return file;
    }

    /**
     * readString() asks the user to input an String using a message,
     *
     * @return String answer: the string read from the user.
     */
    public String readString(String message) {
        String answer = null;
        Scanner scan = new Scanner(System.in);
        System.out.println(message);
        answer = scan.next();
        return answer;
    }

    private void showPlayersList(List<Player> players) {
        for(int i=0; i<players.size();i++){
        System.out.println(players.get(i).toString());;
        }
    }
}

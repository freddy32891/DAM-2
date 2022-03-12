package cat.proven.gameapp.model;

import cat.proven.gameapp.exceptions.DuplicatedPlayerException;
import cat.proven.gameapp.exceptions.FirstCurrentPlayerException;
import cat.proven.gameapp.exceptions.LastCurrentPlayerException;
import cat.proven.gameapp.exceptions.NullPlayerException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class Game {

    //Attributes
    private int currentPlayer;
    private List<Player> players;

    //Constructors
    public Game() {
        this.players = new ArrayList<>();
        currentPlayer = 0;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Method that catches all the players in the arrayList
     *
     * @return all the players in the arrayList
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Method that adds a player in the arrayList of players
     *
     * @param player
     * @return true if sucesfully added, and false otherwise
     * @throws DuplicatedPlayerException if the player given already exists
     */
    public boolean addPLayer(Player player) throws DuplicatedPlayerException{
        boolean succesfullyAdded = false;
        if (player != null) {
            if (!players.contains(player)) {
                succesfullyAdded = players.add(player);
            } else {
                throw new DuplicatedPlayerException("The player given already exists");
            }
        } else {
            succesfullyAdded=false;
        }
        return succesfullyAdded;
    }

    /**
     * Method that moves the position of currencyPlayer to the next Position
     * @return the currency player in the next position
     * @throws LastCurrentPlayerException if the position of the currencyplayer before moving to the next position is the last
     * @throws NullPlayerException if the player we're going to return is null
     */
    public Player nextPlayer() throws LastCurrentPlayerException, NullPlayerException {
        Player player = null;
        if (currentPlayer < players.size()-1) {
            currentPlayer++;
            player = players.get(currentPlayer);
            if (player != null) {
                return player;
            } else {
                throw new NullPlayerException("The player is null");
            }
        } else {
            throw new LastCurrentPlayerException("The player is in the last position, so there's no next player");
        }

    }
    /**
     * Method that moves the position of the CUrrencyPLayer to the previous position
     * @return the player in the previous position
     * @throws FirstCurrentPlayerException if the position of the current player is the first in the arrayList
     * @throws NullPlayerException if the player we're going to return is null 
     */
    public Player previousPlayer()throws FirstCurrentPlayerException, NullPlayerException{
    Player player = null;
        if (currentPlayer > 0) {
            currentPlayer--;
            player = players.get(currentPlayer);
            if (player != null) {
                return player;
            } else {
                throw new NullPlayerException("The player is null");
            }
        } else {
            throw new FirstCurrentPlayerException("The player is in the first position, so there's no previous player");
        }
    }
    
    /**
     * Methos thats catches the current player
     * @return the curent player
     */
    public Player showCurrentPlayer(){
    return players.get(currentPlayer);
    }
    
    /**
     * Method that removes the currencyPLayer
     * @return true if succesfully removed or false in case of error
     */
    public boolean removeCurrentPlayer(){
    boolean correctlyRemoved=false;
    Player player= players.get(currentPlayer);
    if(player!=null){
    players.remove(player);
    }else {
    correctlyRemoved= false;
    }
    return correctlyRemoved;
    }

}

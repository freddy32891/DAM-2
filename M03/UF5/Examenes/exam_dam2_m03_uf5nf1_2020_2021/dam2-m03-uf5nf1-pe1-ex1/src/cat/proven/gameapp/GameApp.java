package cat.proven.gameapp;

/**
 * PlayersApp.java Application with menu.
 *
 * @author
 */
import cat.proven.gameapp.exceptions.DuplicatedPlayerException;
import cat.proven.gameapp.exceptions.FirstCurrentPlayerException;
import cat.proven.gameapp.exceptions.LastCurrentPlayerException;
import cat.proven.gameapp.exceptions.NullPlayerException;
import cat.proven.gameapp.menus.PlayersMainMenu;
import cat.proven.gameapp.menus.Menu;
import cat.proven.gameapp.model.Game;
import cat.proven.gameapp.model.Player;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameApp {

    /* ------- attributes, properties or fields ------- */
    private Menu mainMenu;
    private Game myGame;

    /**
     * main(). Starts up application execution.
     */
    public static void main(String[] args) {
        /* application object */
        GameApp myAp = new GameApp();
        /* run the application */
        myAp.run();

    }

    /* -------------- methods -------------- */
    /**
     * run() runs the application in non-static mode.
     */
    private void run() {
        /* build main menu */
        mainMenu = new PlayersMainMenu("Players main menu");
        /* exit flag */
        boolean exit = false;
        /* menu option to execute */
        int option;
        /* instantiate data */
        myGame = new Game();
        /* load data */
        loadData();
        /* user service loop  */
        do {
            //show menu
            mainMenu.show();
            // get option
            option = mainMenu.choose();
            //control block
            switch (option) {
                case 0: //exit
                    exit = true;  //set the exit flag.
                    break;
                case 1: //next player.
                    nextPlayer();
                    break;
                case 2: //previous player.
                    previousPlayer();
                    break;
                case 3: //show current player.
                    showCurrentPlayer();
                    break;
                case 4: //list all players.
                    listAllPlayers();
                    break;
                case 5: //add new player.
                    addNewPlayer();
                    break;
                case 6: //remove current player.
                    removeCurrentPlayer();
                    break;
                default: //default or invalid option
                    alert("Invalid option\n");
                    break;
            }
        } while (!exit);

        alert("Closing application ...\n");
    }

    /**
     * alert() shows a message
     *
     * @param String msg: message to show
     */
    private void alert(String msg) {
        System.out.print(msg);
    }

    /**
     * loadData() loads and initializes data
     */
    private void loadData() {
        //TODO: load test data.
        myGame.getPlayers().add(new Player("FR", "FREDDY", 2, 8));
        myGame.getPlayers().add(new Player("JU", "JUAN", 1, 6));
        myGame.getPlayers().add(new Player("MA", "MARTIN", 4, 5));
        myGame.getPlayers().add(new Player("PE", "PEDRO", 2, 3));
        myGame.getPlayers().add(new Player("CA", "CARLOS", 4, 10));
    }

    /**
     * readString() asks the user to input an String using a message,
     *
     * @return String answer: the string read from the user.
     */
    public String readString(String message) {
        String answer = null;
        Scanner scan = new Scanner(System.in);
        alert(message);
        answer = scan.next();
        return answer;
    }

    /**
     * Method that moves the positio of the current player to the next one
     */
    private void nextPlayer() {
        boolean result = true;
        try {
            int previous = myGame.getCurrentPlayer();
            Player player = myGame.nextPlayer();
            int next = myGame.getCurrentPlayer();
            if (result) {
                System.out.println("Correctly moved from position " + previous + " to the next position");
                System.out.println("The new currency player now is: \n" + player.toString());
            }
        } catch (LastCurrentPlayerException ex) {
            alert("The player is in the last position, so there's no next player\n");
            result = false;
        } catch (NullPlayerException ex) {
            result = false;
        }
    }

    /**
     * Method that moves the positio of the current player to the previous one
     */
    private void previousPlayer() {
        boolean result = true;
        try {
            int previous = myGame.getCurrentPlayer();
            Player player = myGame.previousPlayer();
            int next = myGame.getCurrentPlayer();
            System.out.println("Correctly moved from position " + previous + " to the next position");
            System.out.println("The new currency player now is: \n" + player.toString());

        } catch (NullPlayerException ex) {
            result = false;
        } catch (FirstCurrentPlayerException ex) {

            result = false;
        }
        if (!result) {
            alert("The player is in the first position, so there's no next player\n");
        }
    }

    /**
     * Method that shows the current player
     */
    private void showCurrentPlayer() {
        Player player = myGame.showCurrentPlayer();
        if (player != null) {
            System.out.println(player.toString());
        } else {
            alert("Current player is not founded");
        }
    }

    /**
     * Method that lists all players
     */
    private void listAllPlayers() {
        List<Player> players = myGame.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).toString());
        }
        System.out.println("There are " + players.size() + " players founded");
    }

    /**
     * Method that adds a new player
     */
    private void addNewPlayer() {
        Player player = inputPlayer();
        boolean result = false;
        if (player != null) {
            try {
                result = myGame.addPLayer(player);
            } catch (DuplicatedPlayerException ex) {
                result = false;
            }
            if (result) {
                System.out.println("Player succesfully added");
            } else {
                System.out.println("PLayer not added");
            }
        }
    }

    /**
     * Method that removes the current player
     */
    private void removeCurrentPlayer() {
    boolean result=false;
    Player player=myGame.showCurrentPlayer();
    if(player!=null){
        System.out.println("The current player is \n"+player.toString());
        String option=readString("Are you sure you want to delete this player? (Y/N)");
        if(option.equalsIgnoreCase("Y")){
        result=myGame.removeCurrentPlayer();
        System.out.println("Current player is correctly removed now the current player is\n"+myGame.showCurrentPlayer().toString());

        }else{
            result = false;
            System.out.println("CUrrent player not removed");
        }
    }else{
        System.out.println("There's no current player");
    }

    }

    /**
     * Method that asks the user to input a player
     * @return the player given by user
     */
    private Player inputPlayer() {
        Player player = null;
        String id = readString("Input the id: ");
        String name = readString("Input the name: ");
        try {
            String sLevel = readString("Input the level: ");
            int price = Integer.parseInt(sLevel);
            String statistics = readString("Input the statistics: ");
            double stock = Double.parseDouble(statistics);
            player = new Player(id, name, price, stock);

        } catch (NumberFormatException e) {
            player = null;
        }
        return player;
    }

}

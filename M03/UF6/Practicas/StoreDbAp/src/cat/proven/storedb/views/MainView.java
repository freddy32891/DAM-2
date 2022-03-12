package cat.proven.storedb.views;

import cat.proven.storedb.controllers.MainController;
import cat.proven.storedb.model.Model;
import cat.proven.storedb.model.Product;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author mati
 */
public class MainView {

    private MainController controller;
    private boolean exit;
    private MainMenu mainMenu;

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    
    public MainView(MainController controller) {
        this.controller = controller;
        this.mainMenu = new MainMenu();
    }

    public MainView(MainController aThis, Model model) {
    }

    public void show() {
        this.exit = false;
        do {
            mainMenu.show();
            String action=mainMenu.getSelectedOptionActionCommand();
            controller.processAction(action);
        } while (!exit);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
    /**
     * Method that prompts a message and reads and answer from user
     * @param message the message to prompt
     * @return user's answer or null in case of error.
     */
    public String inputString(String message){
        System.out.println(message);
        Scanner sc= new Scanner(System.in);
        return sc.nextLine();

    }

    public void displayProductTable(List<Product> data) {
        if(data!=null){
        for(Product products: data){
            System.out.println(products.toString());
        }
        }
    }

    /**
     * Displays the product given
     * @param p product given
     */
    public void displayProduct(Product p) {
        System.out.println(p);
    }

    public int askForLowStock(String input_the_minimum_number_of_stock) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Product inputProduct() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

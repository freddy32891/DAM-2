/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.flights;

import cat.proven.flights.controllers.MainController;
import cat.proven.flights.model.Model;

/**
 * Main class for flights application
 *
 * @author freddy
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Model model = new Model();
            MainController controller = new MainController(model);
            controller.start();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

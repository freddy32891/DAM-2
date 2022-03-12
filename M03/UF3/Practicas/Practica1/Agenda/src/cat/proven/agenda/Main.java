/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.agenda;

import cat.proven.agenda.controllers.MainController;
import cat.proven.agenda.model.Model;

/**
 *
 * @author fredd
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Model model = new Model();
        MainController controller = new MainController(model);
        controller.getView().show();
    }
    
}

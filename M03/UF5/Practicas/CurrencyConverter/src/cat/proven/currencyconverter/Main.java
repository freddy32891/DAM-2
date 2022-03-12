/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.currencyconverter;

import cat.proven.currencyconverter.controller.ViewController;
import cat.proven.currencyconverter.model.CurrencyConverter;
import cat.proven.currencyconverter.views.MainFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author fredd
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        CurrencyConverter currencyConverter = new CurrencyConverter();
        ViewController controller=new ViewController(currencyConverter);
        SwingUtilities.invokeLater(new Runnable(){
        @Override
        public void run(){
        MainFrame frame= new MainFrame(controller);
        controller.setFrame(frame);
        frame.setVisible(true);
        }
        });
        
    }

}

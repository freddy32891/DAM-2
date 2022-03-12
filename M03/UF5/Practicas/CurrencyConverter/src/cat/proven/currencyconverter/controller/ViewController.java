/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.currencyconverter.controller;

import cat.proven.currencyconverter.model.Coin;
import cat.proven.currencyconverter.model.CurrencyConverter;
import cat.proven.currencyconverter.views.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author mati
 */
public class ViewController implements ActionListener {

    private MainFrame frame;
    private CurrencyConverter model;

    public ViewController(CurrencyConverter model) {
        this.model = model;
    }

    public CurrencyConverter getModel(){
    return model;
    }
    public MainFrame getFrame(){
    return frame;
    }
    public void setFrame(MainFrame frame){
    this.frame=frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action != null) {
            System.out.println("Executing " + action);
            switch (action) {
                case "exit"://exit application
                    exitAplication();
                    break;
                case "about": //display about dialog
                    frame.displayAboutDialog();
                    break;
                case "convertForm": //Show convert panel
                    frame.convertForm();
                    break;
                case "convert":
                    doConvert(); //Converts
                    break;
                case "setRatio": //set ratio
                    setRatio();
                    break;
                case "ratio": //Show setRatio panel
                    frame.ratio();
                    break;
                case "createTable": //Show currency table
                    frame.createTable();
                    break;
                default:
                    break;
            }
        }
    }
    
    public void doConvert() {
        try{
        String combo1 = (String) frame.getConvertPanel().getcombo1().getSelectedItem();
        String combo2 = (String) frame.getConvertPanel().getcombo2().getSelectedItem();
                
        double amount = Double.parseDouble(frame.getConvertPanel().gettfInitialCoin().getText()); 
        double result=model.change(combo1, combo2, amount);
        double rattio=model.returnRatio(amount, result);
        frame.getConvertPanel().gettfConvertedCoin().setText(String.valueOf(result));
        frame.getConvertPanel().getTfRatio().setText("1 "+combo1+" = "+String.valueOf(rattio)+" "+ combo2);
        
        }catch(NumberFormatException e){
        JOptionPane.showMessageDialog(frame,"Bad value", "Validation error", JOptionPane.ERROR_MESSAGE);
        frame.getConvertPanel().setTextOfTfInitialCoin("");
        }
    }
    
    public void setRatio() {
        try{
        String combo1 = (String) frame.getRatioPanel().getCombo1().getSelectedItem();
        Coin coin= model.getCoinByType(combo1);
        Double value=Double.parseDouble(frame.getRatioPanel().getTfRatio().getText());
        coin.setValue(value);
        JOptionPane.showMessageDialog(frame, "Ratio correctly changed");
        }
        catch(NumberFormatException e){
        JOptionPane.showMessageDialog(frame,"Bad value", "Validation error", JOptionPane.ERROR_MESSAGE);
        frame.getRatioPanel().getTfRatio().setText("");
        }
    }
    
    /**
     * Method to close the aplication if the answer of the is YES, otherwise it doesn't close the app.
     */
    public void exitAplication() {
        int answer = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Exit", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

}

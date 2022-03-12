/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.currencyconverter.views;

import cat.proven.currencyconverter.model.CurrencyConverter;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author freddy
 */
public class SelectDivisa extends JPanel{
    private JComboBox<String> combo;
    private ActionListener listener;
    private CurrencyConverter currency;

    public SelectDivisa(ActionListener listener) {
        this.listener=listener;
        initComponents();
    }

    private void initComponents() {
        currency= new CurrencyConverter();
        combo= new JComboBox<>();
        List <String>divisa= currency.getCurrencyNames();
        for(int i=0;i<divisa.size();i++){
        combo.addItem(divisa.get(i));
        }
        
    }
    
}

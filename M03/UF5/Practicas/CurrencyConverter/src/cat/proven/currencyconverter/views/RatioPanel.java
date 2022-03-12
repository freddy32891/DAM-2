/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.currencyconverter.views;

import cat.proven.currencyconverter.controller.ViewController;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author fredd
 */
public class RatioPanel extends JPanel{
    private MainFrame parent;
    private ActionListener listener;
    private JTextField tfRatio;
    private JComboBox<String>combo1;
    private ViewController controller;

    //Constructor
    public RatioPanel(ViewController controller){
        this.controller=controller;
        this.listener=controller;
        initComponents();
    }
    
    //getters
    public JTextField getTfRatio() {
        return tfRatio;
    }

    public JComboBox<String> getCombo1() {
        return combo1;
    }


    /**
     * Method that creates all the elements in the view
     */
    private void initComponents() {
        setLayout(new GridLayout(3,4));
        
        combo1= new JComboBox<>();
        addValuesToComboBox(combo1);
        
        JLabel lbRatio= new JLabel("1");
        JLabel divisa= new JLabel("USD");
        //JLabel label = new JLabel(" = ");
        tfRatio=new JTextField(20);
        add(lbRatio); add(combo1); //add(label); 
        add(tfRatio); add(divisa);
        
        JButton btSetRatio= new JButton("set ratio");
        btSetRatio.setActionCommand("setRatio");
        btSetRatio.addActionListener(listener);
        add(btSetRatio);
    }

    
    /**
     * Method that adds all the items in the comboBOx
     * @param combo where is going to add the items
     */
    private void addValuesToComboBox(JComboBox<String> combo){
    List<String>names=controller.getModel().getCurrencyNames();
    for(int i=0;i<names.size();i++){
        combo.addItem(names.get(i));
        }
    }
}

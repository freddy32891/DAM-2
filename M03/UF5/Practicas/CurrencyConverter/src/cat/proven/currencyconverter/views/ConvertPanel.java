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
 * @author mati
 */
public class ConvertPanel extends JPanel {
    private MainFrame parent;
    private ActionListener listener;
    private JTextField tfInitialCoin;
    private JTextField tfConvertedCoin;
    private JTextField tfRatio;
    private JComboBox<String> combo1;
    private JComboBox<String> combo2;
    private ViewController controller;
    
    public ConvertPanel(ViewController controller) {
        this.controller=controller;
        this.listener=controller;
        initComponents();
    }

    public JTextField gettfInitialCoin(){
    return tfInitialCoin;
    }
    public JTextField gettfConvertedCoin(){
    return tfConvertedCoin;
    }
    
    public JComboBox getcombo1(){
    return combo1;
    }
    public JComboBox getcombo2(){
    return combo2;
    }

    public JTextField getTfRatio() {
        return tfRatio;
    }
    

    public void setTextOfTfInitialCoin(String tfInitialCoin) {
        this.tfInitialCoin.setText(tfInitialCoin);
    }

    private void initComponents() {
        setLayout(new GridLayout(4,2));
        
        combo1= new JComboBox<>();
        combo2= new JComboBox<>();
        addValuesToComboBox(combo1,combo2);
        
        tfInitialCoin= new JTextField(20);
        tfConvertedCoin= new JTextField(20);
        
        JLabel lbRatio = new JLabel("Ratio: ");
        tfRatio= new JTextField(20);
        
        add(combo1); add(tfInitialCoin); 
        
        add(combo2); add(tfConvertedCoin);
        
        add(lbRatio); add(tfRatio);
        JButton btConvert= new JButton("Convert");
        btConvert.setActionCommand("convert");
        btConvert.addActionListener(listener);
        add(btConvert);
        
        
    }
    
    private void addValuesToComboBox(JComboBox<String> combo1,JComboBox<String> combo2){
    List<String>names=controller.getModel().getCurrencyNames();
    for(int i=0;i<names.size();i++){
        combo1.addItem(names.get(i));
        combo2.addItem(names.get(i));
        }
    }


    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.currencyconverter.views;

import cat.proven.currencyconverter.controller.ViewController;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author fredd
 */
public class TablePanel extends JPanel{

    private JTable table;
    private ViewController controller;
    
    public TablePanel(ViewController controller){
    this.controller=controller;
    initComponents();
    }
    private void initComponents() {
        setLayout(new BorderLayout());
        table = new DivisaTable(controller);
        JScrollPane scrollPane = new JScrollPane(table);     
        add(scrollPane, BorderLayout.CENTER);
    }
    
    
}

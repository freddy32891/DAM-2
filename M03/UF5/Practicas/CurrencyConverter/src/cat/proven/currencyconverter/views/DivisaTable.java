/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.currencyconverter.views;

import cat.proven.currencyconverter.controller.ViewController;
import cat.proven.currencyconverter.model.DivisaTableModel;
import javax.swing.JTable;

/**
 *
 * @author fredd
 */
public class DivisaTable extends JTable {
    
    private ViewController controller;
    
    public DivisaTable(ViewController controller) {
    this.controller=controller;
    setModel(new DivisaTableModel(controller));       
        setAutoCreateRowSorter(true);       
        setFillsViewportHeight(true); 
        setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
    }

  
    
}

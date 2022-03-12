/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.currencyconverter.model;

import cat.proven.currencyconverter.controller.ViewController;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author fredd
 */
public class DivisaTableModel extends AbstractTableModel{

    private ViewController controller;
    
    String [] columnNames={"Divisa","Value"};
    //Constructor
    public DivisaTableModel(ViewController controller){
    this.controller=controller;
    addTableModelListener( new DivisaTableModelListener() );
    }
    
    /**
     * Method that counts the number of files the table
     * @return the number of files
     */
    @Override
    public int getRowCount() {
        return controller.getModel().getCoins().size();
    }

    /**
     * Method that returns the number of columns in the table
     * @return the number of columns in the table
     */
    @Override
    public int getColumnCount() {
        return 2; 
    }

    /**
     * Method that catches all the coins and insert in the table
     * @param rowIndex the row in the table
     * @param columnIndex the column in the table
     * @return a coin
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Coin elem = controller.getModel().getCoins().get(rowIndex);
        Object value = null;
        switch (columnIndex) {
            case 0:
                value = elem.getType();
                break;
            case 1:
                value = elem.getValue();
                break;
            default:
                value = null;
                break;
        }   
        return value;
    }

    /**
     * Method that reads the name of the column
     * @param col the column where is in the table
     * @return the name of the column
     */
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    /**
     * Method that reads the class in the table by a column
     * @param col the column in the table
     * @return the class of the object
     */
    @Override
    public Class getColumnClass(int col) {
        return getValueAt(0, col).getClass();
    } 
    
    /**
     * Method that 
     * @param value
     * @param row
     * @param col 
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        Coin elem = controller.getModel().getCoins().get(row);
        switch (col) {
            case 0:
                elem.setType((String) value);
                break;
            case 1:
                elem.setValue((double) value);
                break;
            default:
                break;
        }
        fireTableCellUpdated(row, col);
    } 
    
    /**
     * Method that selects all the editable cell
     * @param row given
     * @param col given
     * @return true if the cell is editabel, false if no
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    }    
    
}

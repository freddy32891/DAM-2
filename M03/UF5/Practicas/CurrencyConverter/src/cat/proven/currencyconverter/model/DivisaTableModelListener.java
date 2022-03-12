/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.currencyconverter.model;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author fredd
 */
public class DivisaTableModelListener implements TableModelListener {

    /**
     * Method that realizes when the table has changed and comunicates this change to user
     * @param e the event 
     */
    @Override
    public void tableChanged(TableModelEvent e) {
        if(e.getType()==TableModelEvent.UPDATE){
        TableModel model =(TableModel)e.getSource();
        int row= e.getFirstRow();
        int col= e.getColumn();
        Object value= model.getValueAt(row, col);
        System.out.format("Changed row %d colum %d to value %s\n", row, col, value.toString());
        }
    }


    
}

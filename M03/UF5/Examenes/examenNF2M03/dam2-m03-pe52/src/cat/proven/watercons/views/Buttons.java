/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.watercons.views;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author mati
 */
public class Buttons extends JPanel {

    //attributes
    private JButton btCalculate;
    private JButton btClear;

    //getters
    public JButton getBtCalculate() {
        return btCalculate;
    }

    public JButton getBtClear() {
        return btClear;
    }

    public JButton getBtInfo() {
        return btInfo;
    }
    private JButton btInfo;
    ActionListener listener;

    public Buttons(MainFrame frame) {
        this.listener = frame;
        initComponents();
    }

    /**
     * Method that creates and adds all the the buttons needed in the panel
     */
    private void initComponents() {
        btCalculate = new JButton("Calculate");
        btCalculate.setActionCommand("calculate");
        btCalculate.addActionListener(listener);
        this.add(btCalculate);

        btClear = new JButton("Clear");
        btClear.setActionCommand("clear");
        btClear.addActionListener(listener);
        this.add(btClear);
        btClear.setEnabled(false);

        btInfo = new JButton("Info");
        btInfo.setActionCommand("info");
        btInfo.addActionListener(listener);
        this.add(btInfo);
    }
}

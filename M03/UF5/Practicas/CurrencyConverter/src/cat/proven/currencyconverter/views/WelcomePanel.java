/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.currencyconverter.views;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author fredd
 */
public class WelcomePanel extends JPanel{
   private String welcomeMessage;
    public WelcomePanel() {
        welcomeMessage="Welcome to Currency converter!";
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        JLabel welcomeLbl = new JLabel(welcomeMessage);
        welcomeLbl.setBackground(Color.CYAN);
        this.add(welcomeLbl, BorderLayout.CENTER);
    } 
}

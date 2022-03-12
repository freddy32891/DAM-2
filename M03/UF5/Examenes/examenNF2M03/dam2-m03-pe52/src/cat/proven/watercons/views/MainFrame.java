/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.watercons.views;

import cat.proven.watercons.model.Consum;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author mati
 */
public class MainFrame extends JFrame implements ActionListener {

    private ActionListener listener;
    private Consum model;
    private String infoMessage;
    private Buttons buttons;
    private Panel panel;

    //constructor
    public MainFrame(Consum model) {
        this.listener = this;
        this.model = model;
        infoMessage = "<html><p><b>INFO MESSAGE</b></p>(C) FreddySoft 2021<p>Water consumption</p></html>";//the infoMEssage
        initComponents();
    }

    /**
     * Method that catches all the commands sended by the listener and executes
     * an action depending on the command received
     *
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action != null) {
            System.out.println("Executing " + action);
            switch (action) {
                case "clear"://exit application
                    clear();
                    buttons.getBtClear().setEnabled(false); //after using it, it turns invisible
                    break;
                case "info": //display info dialog
                    displayInfoDialog();
                    break;
                case "calculate": //calculates
                    calculate();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Method to init all the components needed
     */
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Water consumption");
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                exitAplication();
            }
        });

        Container pane = getContentPane();

        panel = new Panel(this);
        setLayout(new GridLayout(2, 4));
        buttons = new Buttons(this);
        pane.add(panel);
        pane.add(buttons);
        setLocationRelativeTo(null);
        setSize(600, 250);

    }

    /**
     * Method that shows an Option pane for the user to confirm if he wants to
     * exit the application, in case that te answer is yes, the aplications
     * closes, otherwise it continues oppened
     */
    private void exitAplication() {
        int answer = JOptionPane.showConfirmDialog(this, "Are you sure?", "Exit", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

    }

    /**
     * Displays an info message
     */
    public void displayInfoDialog() {
        JOptionPane.showMessageDialog(this, infoMessage);

    }

    /**
     * Method that calculates the amount of water that a person consumes in a
     * day with the given number of persons and the litersPerMonth. It also
     * clasificates the amount of water consumed in "low", "high", "medium" In
     * case that the given numbers are incorrect, it's catched by
     * NUmberFormatException
     */
    private void calculate() {
        try {
            double persons = Double.parseDouble(panel.getTfPersons().getText());
            double literMonth = Double.parseDouble(panel.getTfLitersMonth().getText());
            int result = model.calculate(literMonth, persons);
            panel.getTfLitersPersonDay().setText(String.valueOf(result));

            String lvl = model.level(literMonth, persons);
            panel.getTfLevel().setText(lvl);
            buttons.getBtClear().setEnabled(true);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Bad value", "Validation error", JOptionPane.ERROR_MESSAGE);
            panel.getTfLitersMonth().setText("");
            panel.getTfPersons().setText("");
        }
    }

    /**
     * Mehtod that cleans all the textfields
     */
    private void clear() {
        panel.getTfLevel().setText("");
        panel.getTfLitersMonth().setText("");
        panel.getTfLitersPersonDay().setText("");
        panel.getTfPersons().setText("");
    }
}

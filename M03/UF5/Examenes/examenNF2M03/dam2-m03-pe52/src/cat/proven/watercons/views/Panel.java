/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.watercons.views;

import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author mati
 */
public class Panel extends JPanel {

    //attributes
    private JTextField tfLitersMonth;
    private JTextField tfPersons;
    private JTextField tfLitersPersonDay;
    private JTextField tfLevel;
    private ActionListener listener;

    //constructor
    public Panel(MainFrame frame) {
        this.listener = frame;
        initComponents();
    }

    //setters and getters
    public JTextField getTfLitersMonth() {
        return tfLitersMonth;
    }

    public void setTfLitersMonth(JTextField tfLitersMonth) {
        this.tfLitersMonth = tfLitersMonth;
    }

    public JTextField getTfPersons() {
        return tfPersons;
    }

    public void setTfPersons(JTextField tfPersons) {
        this.tfPersons = tfPersons;
    }

    public JTextField getTfLitersPersonDay() {
        return tfLitersPersonDay;
    }

    public void setTfLitersPersonDay(JTextField tfLitersPersonDay) {
        this.tfLitersPersonDay = tfLitersPersonDay;
    }

    public JTextField getTfLevel() {
        return tfLevel;
    }

    public void setTfLevel(JTextField tfLevel) {
        this.tfLevel = tfLevel;
    }

    public ActionListener getListener() {
        return listener;
    }

    public void setListener(ActionListener listener) {
        this.listener = listener;
    }

    /**
     * Method that creates and adds all the components needed in the JPanel
     */
    private void initComponents() {
        tfLitersMonth = new JTextField(10);
        tfPersons = new JTextField(10);
        tfLitersPersonDay = new JTextField(10);
        tfLitersPersonDay.setEditable(false);
        tfLevel = new JTextField(10);
        tfLevel.setEditable(false);

        JLabel lbLitersMonth = new JLabel("Liters per month ");
        JLabel lbPersons = new JLabel("Number of persons ");
        JLabel lbLitersPersonDay = new JLabel("Liter/person/day ");
        JLabel lbLevel = new JLabel("Level ");

        this.add(lbLitersMonth);
        this.add(tfLitersMonth);
        this.add(lbPersons);
        this.add(tfPersons);
        this.add(lbLitersPersonDay);
        this.add(tfLitersPersonDay);
        this.add(lbLevel);
        this.add(tfLevel);
    }

}

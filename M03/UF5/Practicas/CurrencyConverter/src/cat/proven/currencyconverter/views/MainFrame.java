package cat.proven.currencyconverter.views;

import cat.proven.currencyconverter.controller.ViewController;
import cat.proven.currencyconverter.model.Coin;
import cat.proven.currencyconverter.model.CurrencyConverter;
import cat.proven.currencyconverter.views.ConvertPanel;
import cat.proven.currencyconverter.views.RatioPanel;
import cat.proven.currencyconverter.views.SelectDivisa;
import cat.proven.currencyconverter.views.TablePanel;
import cat.proven.currencyconverter.views.WelcomePanel;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author fredd
 */
public class MainFrame extends JFrame {

    private ActionListener listener;
    private String title;
    private String aboutMessage;
    private JMenuBar menuBar;
    private WelcomePanel welcomePanel;
    private SelectDivisa selectDivisa;
    private ConvertPanel convertPanel;
    private RatioPanel ratio;
    private TablePanel table;
    private ViewController controller;

    public MainFrame(ViewController controller) {
        this.title = "Currency converter application";
        this.controller=controller;
        aboutMessage = "<html><p><b>Unit converter</b></p>(C) ProvenSoft 2020<p>message about</p></html>";
        listener = controller;
        initComponents();
    }

    public ConvertPanel getConvertPanel(){
    return convertPanel;
    }
    public RatioPanel getRatioPanel(){
    return ratio;
    }

    /**
     * Build GUI
     */
    private void initComponents() {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                exitAplication();
            }
        });

        //Build menu
        menuBar = buildMenuBar();
        setJMenuBar(menuBar);
        
        
        Container pane= getContentPane();
        welcomePanel=new WelcomePanel();
        pane.add(welcomePanel);
        
        //load welcome panel
        setLocationRelativeTo(null);
        setSize(400, 300);
    }

    /**
     * Method to close the aplication if the answer of the is YES, otherwise it doesn't close the app.
     */
    public void exitAplication() {
        int answer = JOptionPane.showConfirmDialog(this, "Are you sure?", "Exit", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Method that builds a menuBar
     * @return the menuBar created
     */
    private JMenuBar buildMenuBar() {
        JMenuBar mnuBar = new JMenuBar();
        JMenu mnu;
        JMenuItem mItem;
        //File (Exit), Convert (Temperature), Help (About)
        //File
        mnu = new JMenu("File");
        mnuBar.add(mnu);
        mItem = new JMenuItem("Exit");
        mItem.setActionCommand("exit");
        mItem.addActionListener(listener);
        mnu.add(mItem);
        //Convert
        mnu = new JMenu("Edit");
        mnuBar.add(mnu);
        mItem = new JMenuItem("Convert form");
        mItem.setActionCommand("convertForm");
        mItem.addActionListener(listener);
        mnu.add(mItem);
        mItem = new JMenuItem("set Ratio");
        mItem.setActionCommand("ratio");
        mItem.addActionListener(listener);
        mnu.add(mItem);
        mItem= new JMenuItem("Show currencies");
        mItem.setActionCommand("createTable");
        mItem.addActionListener(listener);
        mnu.add(mItem);
        //Help
        mnu = new JMenu("Help");
        mnuBar.add(mnu);
        mItem = new JMenuItem("About");
        mItem.setActionCommand("about");
        mItem.addActionListener(listener);
        mnu.add(mItem);
        return mnuBar;
    }
    /**
     * Method that catches all the events in the listener and calls other methods
     * @param e actionevent 
     */
//    @Override
//    public void actionPerformed(ActionEvent e){
//    String action = e.getActionCommand();
//    if(action!=null){
//        System.out.println("Executing " + action);
//        switch (action) {
//            case "exit"://exit application
//                exitAplication();
//                break;
//            case "about": //display about dialog
//                displayAboutDialog();
//                break;
//            case "convertForm": //Show convert panel
//                convertForm();
//                break;
//            case "convert":
//                doConvert(); //Converts
//                break;
//            case "setRatio": //set ratio
//                setRatio();
//                break;   
//            case "ratio": //Show setRatio panel
//                ratio();
//                break;
//            case "createTable": //Show currency table
//                createTable();
//                break;
//            default:
//                break;
//            }
//        }
//    }

    /**
     * Method that instanciates a convert panel and shows to the user
     */
    public void convertForm() {
    convertPanel= new ConvertPanel(controller);
    this.setContentPane(convertPanel);
    validate();
    }

    /**
     * Method that changes the ratio of a selected coin.
     */
//    public void setRatio() {
//        try{
//        String combo1 = (String) ratio.getCombo1().getSelectedItem();
//        Coin coin= model.getCoinByType(combo1);
//        Double value=Double.parseDouble(ratio.getTfRatio().getText());
//        coin.setValue(value);
//        JOptionPane.showMessageDialog(this, "Ratio correctly changed");
//        }
//        catch(NumberFormatException e){
//        JOptionPane.showMessageDialog(this,"Bad value", "Validation error", JOptionPane.ERROR_MESSAGE);
//        ratio.getTfRatio().setText("");
//        }
//    }

    /**
     * Displays an About message
     */
    public void displayAboutDialog() {
        JOptionPane.showMessageDialog(this, aboutMessage);

    }

    /**
     * Method that calls to the model to calculate the convertion. and shows the ratio of the coin 
     */
//    public void doConvert() {
//        try{
//        String combo1 = (String) convertPanel.getcombo1().getSelectedItem();
//        String combo2 = (String) convertPanel.getcombo2().getSelectedItem();
//                
//        double amount = Double.parseDouble(convertPanel.gettfInitialCoin().getText()); 
//        double result=model.change(combo1, combo2, amount);
//        double rattio=model.returnRatio(amount, result);
//        convertPanel.gettfConvertedCoin().setText(String.valueOf(result));
//        convertPanel.getTfRatio().setText("1 "+combo1+" = "+String.valueOf(rattio)+" "+ combo2);
//        
//        }catch(NumberFormatException e){
//        JOptionPane.showMessageDialog(this,"Bad value", "Validation error", JOptionPane.ERROR_MESSAGE);
//        convertPanel.setTextOfTfInitialCoin("");
//        }
//    }

    /**
     * Method that instanciates a set ratio panel and shows to the user
     */
    public void ratio() {
        ratio= new RatioPanel(controller);
        this.setContentPane(ratio);
        validate();
    }

    /**
     * Method that instaciates a TablePanel and shows to user
     */
    public void createTable() {  
        table= new TablePanel(controller);
        this.setContentPane(table);
        validate();
    }

}

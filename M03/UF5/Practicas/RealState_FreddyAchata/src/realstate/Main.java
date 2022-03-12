/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realstate;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import realstate.model.Estate;
import realstate.model.RealEstate;
import realstate.model.persist.SaxXmlPersist;

/**
 *
 * @author freddy
 */
public class Main {
private String[] mainMenu = {
        "Exit", "List all estates", "List all houses", "List all premises", 
    };
SaxXmlPersist saxXml= new SaxXmlPersist();
        RealEstate realEstate=saxXml.load(); 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Main main= new Main();
        main.run();
    }
    private void run(){
        int option;
        
        
    do{
       option=showMenuAndReadOption();
        switch (option) {
            case 0:
                
                break;
            case 1:
                listAll();
                break;
            case 2:
                listHouses();
                break;
            case 3:
                listPresmises();
                break;
                         
            default:
                System.out.println("Invalid option");
                break;
        }
       
    }while(option!=0);
    }
    private void listAll(){
    //TODO
        System.out.println("");
    for (Estate estate : realEstate.getEstates()) {
        System.out.println(estate.toString());
    }
        
    }
    private void listHouses(){
    //TODO
    }
    private void listPresmises(){
    //TODO
    }

    private int showMenuAndReadOption() {
        int option = -1;
        for (int i = 0; i < mainMenu.length; i++) {
            System.out.format("%d. %s\n", i, mainMenu[i]);
        }

        try {
            //read option.
            option = inputInt("Select an option: ");
        } catch (InputMismatchException e) {
            //System.out.println(e.getMessage());
            option = -1;
        }
        return option;
    }

    private int inputInt(String message)throws InputMismatchException {
        System.out.println(message);
        Scanner sc=new Scanner(System.in);
        return sc.nextInt();
    }
}

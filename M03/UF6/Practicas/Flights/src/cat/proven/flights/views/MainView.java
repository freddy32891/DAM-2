/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.flights.views;

import cat.proven.flights.controllers.MainController;
import cat.proven.flights.model.Flight;
import cat.proven.flights.model.FlightPassenger;
import cat.proven.flights.model.Passenger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fredd
 */
public class MainView {

    private final MainController controller;
    private boolean exit; //flag to exit application
    private MainMenu mainMenu;

    public MainView(MainController controller) {
        this.controller = controller;
        this.mainMenu = new MainMenu();
    }

    /**
     * Makes the view visible ans starts interacting with user
     */
    public void show() {
        exit = false;
        //control loop for user interaction
        do {
            mainMenu.show();
            String action = mainMenu.getSelectedOptionActionCommand();
            if (action != null) {
                controller.processAction(action);
            }
        } while (!exit);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays a message and gets user's anaswer
     *
     * @param message the message to display
     * @return te user's answer or null in case of error
     */
    public String inputString(String message) {
        System.out.println(message);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Activates view closing
     */
    public void close() {
        this.exit = true;
    }

    /**
     * Method that shows a flight list
     *
     * @param data the arraylist of flights to show
     */
    public void showFlightList(List<Flight> data) {
        if (data != null) {
            for (Flight flight : data) {
                System.out.println(flight.toString());
            }
            System.out.format("%d elements displayed\n", data.size());
        }

    }

    /**
     * Method that ask the user all the parameters of a flight
     *
     * @return the flight selected by user or null in case of error
     */
    public Flight inputFlight() {
        Flight flight = null;
        try {
            String code = inputString("Input code: ");
            String sCapacity = inputString("Input capacity: ");
            Date date = inputDate("Input date: ");
            Date time = inputTime("Input time: ");
            flight = new Flight(0, code, Integer.parseInt(sCapacity), date, time);
        } catch (ParseException ex) {
            flight=null;
        }

        return flight;
    }

    /**
     * Method that prints a message and asks the user a confirmation
     *
     * @param message message to show
     * @return true if the user introduces an Y as an answer, false otherwise
     */
    public boolean confirmation(String message) {
        String confirmation = inputString(message);
        confirmation = confirmation.toUpperCase();
        if (confirmation.contains("Y")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method that displays a flight given
     *
     * @param result the flight to show
     */
    public void displayFlight(Flight result) {
        System.out.println(result.toString());
    }

    /**
     * Method that ask the user all the parameters of a passenger
     *
     * @return the passenger selected by user
     */
    public Passenger inputPassenger() {
        Passenger result = null;
        String name = inputString("Input name: ");
        String phone = inputString("Input phone: ");
        boolean minor = confirmation("Is minor? (Y/N)");
        result = new Passenger(0, name, phone, minor);
        return result;
    }

    /**
     * Method that shows a passenger list
     *
     * @param data the arraylist of passengers to show
     */
    public void showPassengerList(List<Passenger> data) {
        if (data != null) {
            for (Passenger p : data) {
                System.out.println(p.toString());
            }
            System.out.format("%d elements displayed\n", data.size());
        }
    }

    /**
     * Method thats asks the user to input time
     * @param message
     * @return
     * @throws ParseException 
     */
    private Date inputTime(String message) throws ParseException {
        java.util.Date time = null;
        System.out.println(message);
        String hour = inputString("Input hour: (hh)");
        String minutes = inputString("Input minutes: (mm)");
        time = (Date) new SimpleDateFormat("HH:mm").parse(hour + ":" + minutes);

        return time;
    }

    /**
     * Method that asks user to input date
     * @param message
     * @return
     * @throws ParseException 
     */
    private Date inputDate(String message) throws ParseException {
        System.out.println(message);
        java.util.Date date = null;
        String day = inputString("Day: (dd)");
        String month = inputString("Month: (mm)");
        String year = inputString("Year: (yyyy)");
        
        date = (Date) new SimpleDateFormat("dd-mm-yyyy").parse(day+"-"+month+"-"+year);
        return date;
    }

    public void showFlightPassengerList(List<FlightPassenger> passeengers) {
        for (int i = 0; i < passeengers.size(); i++) {
            System.out.println(passeengers.get(i).toString());
        }
    }
}

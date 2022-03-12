/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.flights.views;

/**
 * Main meenu for flights application
 *
 * @author fredd
 */
public class MainMenu extends Menu {

    public MainMenu() {
        super("Flight application main menu");

        addOption(new Option("Exit application", "exit"));

        addOption(new Option("List all flights", "listAllFlights"));
        addOption(new Option("Add flight", "addFlight"));
        addOption(new Option("Modify flight", "modifyFlight"));
        addOption(new Option("Remove fight", "removeFlight"));

        addOption(new Option("List all passenger", "listAllPassengers"));
        addOption(new Option("Add passenger", "addPassenger"));
        addOption(new Option("Modify passenger", "modifyPassenger"));
        addOption(new Option("Remove passenger", "removePassenger"));

        addOption(new Option("List passengers by flight", "listPassengersByFlight"));
        addOption(new Option("Register passenger to flight", "registerPassengerToFlight"));
        addOption(new Option("Unregister passenger from flight", "unregisterPassengerFromFlight"));

    }
}

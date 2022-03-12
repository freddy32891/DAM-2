/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.flights.controllers;

import cat.proven.flights.model.Flight;
import cat.proven.flights.model.FlightPassenger;
import cat.proven.flights.model.Model;
import cat.proven.flights.model.Passenger;
import cat.proven.flights.views.MainView;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fredd
 */
public class MainController {

    private Model model;
    private final MainView view;

    public MainController(Model model) {
        this.model = model;
        view = new MainView(this);
    }

    public Model getModel() {
        return model;
    }

    public void start() {
        view.show();
    }

    public void processAction(String action) {
        if (action != null) {
            switch (action) {
                case "exit"://exit application
                    exitApplication();
                    break;
                case "listAllFlights"://list all flights
                    doListAllFlights();
                    break;
                case "listAllPassengers":
                    doListAllPassengers();
                    break;
                case "addFlight":
                    addFlight();
                    break;
                case "removeFlight":
                    removeFlight();
                    break;
                case "modifyFlight":
                    modifyFlight();
                    break;
                case "addPassenger":
                    addPassenger();
                    break;
                case "removePassenger":
                    removePassenger();
                    break;
                case "modifyPassenger":
                    modifyPassenger();
                    break;
                case "listPassengersByFlight":
                    listPassengersByFlight();
                    break;
                case "registerPassengerToFlight":
                    registerPassengerToFlight();
                    break;
                case "unregisterPassengerFromFlight":
                    unregisterPassengerFromFlight();
                    break;
                default:
                    view.showMessage("Uknown option");
                    break;
            }
        }
    }

    /**
     * Exits application
     */
    private void exitApplication() {
        String answer = view.inputString("Exit. Are you sure (Y/N): ");
        if (answer != null) {
            if (answer.equalsIgnoreCase("y")) {
                view.close();
            }
        }
    }

    /**
     * List all flighhts from database
     */
    private void doListAllFlights() {
        //retrieves data from model
        List<Flight> result = model.searchAllFlighhts();
        if (result != null) {
            view.showFlightList(result);
        } else {//error retrieving data
            view.showMessage("Error retrieving data");
        }
    }

    private void doListAllPassengers() {
        List<Passenger> result = model.searchAllPassengers();
        if (result != null) {
            view.showPassengerList(result);
        } else {//error retrieving data
            view.showMessage("Error retrieving data");
        }
    }

    private void addFlight() {
        //ask the user the flight to add
        Flight flight = view.inputFlight();
        if (flight != null) {
            int result = model.addFlight(flight);
            if (result == 1) {
                view.showMessage("Flight succesfully added");
            } else if (result == 0) {
                view.showMessage("There is a problem with the sql consult");
            } else if (result == -1) {
                view.showMessage("Error at input flight");
            } else if (result == -11) {
                view.showMessage("The code is duplicated");
            } else if (result == -3) {
                view.showMessage("There is a problem with the database connection");
            } else if (result == -2) {
                view.showMessage("There is an error in the consult");

            }
        } else {
            view.showMessage("Error at input flight");
        }
    }

    /**
     * Method that rempves a flight into the database asking the user the code
     */
    private void removeFlight() {
        //     * Read code
        String code = view.inputString("Input code");
        if (code != null) {
//            * Search flight 

            long id = model.searchFlightId(code);
            Flight result = model.searchFlightById(id);
            if (result != null) {
//                * DIsplay flight
                view.displayFlight(result);
                boolean answer = view.confirmation("Is this the flight you want to update? (Y/N)");
                if (answer) {
                    //     * delete flight
                    int update = model.removeFlight(result);
                    if (update == 1) {
                        view.showMessage("Flight succesfully removed");
                    } else {
                        if (update == 0) {
                            view.showMessage("There is an error in the sql consult");
                        } else if (update == -1) {
                            view.showMessage("There is an error in the database");
                        } else if (update == -2) {
                            view.showMessage("There is an error in the connection with the database");
                        } else if (update == -3) {
                            view.showMessage("There is an error with the given flight");
                        }
                        view.showMessage("Flight not modified");
                    }
                } else {
                    view.showMessage("The flight was not removed");
                }
            } else {
                view.showMessage("Flight not founded");
            }
        }
    }

    private void modifyFlight() {
//     * Read code
        String code = view.inputString("Input code");
        if (code != null) {
//            * Search flight (id)
            long id = model.searchFlightId(code);
            Flight result = model.searchFlightById(id);
            if (result != null) {
//                * DIsplay flight
                view.displayFlight(result);
                boolean answer = view.confirmation("Is this the flight you want to update? (Y/N)");
                if (answer) {
//                    * Read new data for flight from user
                    Flight newFlight = view.inputFlight();
                    System.out.println(newFlight.toString() + "newwww");
                    if (newFlight != null) {
                        newFlight.setId(result.getId());//copying the same id to the new flight
                        //     * Modify flight
                        System.out.println("Beforepdate" + newFlight.toString());
                        int update = model.updateFlight(newFlight);
                        if (update == 1) {
                            view.showMessage("Flight succesfully modified");
                        } else {
                            if (update == 0) {
                                view.showMessage("There is a problem in the flight modification");

                            } else if (update == -1) {
                                view.showMessage("There is an error in the database");

                            } else if (update == -2) {
                                view.showMessage("There is an error in the connection with the database");
                            }
                            view.showMessage("Flight not modified");
                        }
                    } else {
                        view.showMessage("There is a problem in the flight modification");
                    }

                } else {
                    view.showMessage("The flight was not modified");
                }

            } else {
                view.showMessage("The given flight doesn't exist");
            }

        }

    }

    private void addPassenger() {
        Passenger passenger = view.inputPassenger();
        if (passenger != null) {
            int result = model.addPassenger(passenger);
            if (result == 1) {
                view.showMessage("Passenger succesfully added");
            } else if (result == 0) {
                view.showMessage("Error at exxecuting query");
            } else if (result == -1) {
                view.showMessage("Error in the consult");
            } else if (result == -11) {
                view.showMessage("The phone number given already exists");
            } else if (result == -2) {
                view.showMessage("The given flight is not allowed");
            }
        } else {
            view.showMessage("The given flight is not allowed");
        }
    }

    /**
     * Method that removes a passenger
     */
    private void removePassenger() {
        String phone = view.inputString("Input phone");
        if (phone != null) {
//            * Search passenger (id)
            long id = model.searchPassengerId(phone);
            Passenger result = model.searchPassengerById(id);
            if (result != null) {
//                * DIsplay flight
                System.out.println("Passenger founded");
                view.showMessage(result.toString());
                boolean answer = view.confirmation("Is this the passenger you want to delete? (Y/N)");
                if (answer) {
                    //     * remove flight
                    int remove = model.removePassenger(result);
                    if (remove == 1) {
                        view.showMessage("Passenger succesfully removed");
                    } else {
                        if (remove == 0) {
                            view.showMessage("There is a problem in the sql query");
                        } else if (remove == -1) {
                            view.showMessage("There is aproblem with the database");
                        } else if (remove == -2) {
                            view.showMessage("There is an error in the connection with the database");
                        } else if (remove == -3) {
                            view.showMessage("The passenger given is not allowed");
                        }
                        view.showMessage("Passenger not removed");
                    }

                } else {
                    view.showMessage("The passenger was not removed");
                }
            } else {
                view.showMessage("Passenger not founded");
            }
        }
    }

    /**
     *
     */
    private void modifyPassenger() {
        //     * Read code
        String phone = view.inputString("Input phone");
        if (phone != null) {
//            * Search passenger (id)
            long id = model.searchPassengerId(phone);
            Passenger result = model.searchPassengerById(id);
            if (result != null) {
//                * DIsplay flight
                view.showMessage(result.toString());
                boolean answer = view.confirmation("Is this the passenger you want to update? (Y/N)");
                if (answer) {
//                    * Read new data for passenger from user
                    Passenger newPasseger = view.inputPassenger();
                    if (newPasseger != null) {
                        newPasseger.setId(result.getId()); //giving to the new passenger the value of the lastId
                        //     * Modify passenger
                        int update = model.updatePassenger(newPasseger);
                        if (update == 1) {
                            view.showMessage("Passenger succesfully modified");
                        } else {
                            if (update == 0) {
                                view.showMessage("There is a problem in the consult");
                            } else if (update == -1) {
                                view.showMessage("There is an error in the database");
                            } else if (update == -2) {
                                view.showMessage("There is an error in the connection with the database");
                            } else if (update == -3) {
                                view.showMessage("There is a problem in the passengers modification ");
                            } else if (update == -11) {
                                view.showMessage("The phone number given, already exists");
                            }
                            view.showMessage("Passenger not modified");
                        }
                    } else {
                        view.showMessage("There is a problem in the passengers modification 11");
                    }
                } else {
                    view.showMessage("The passenger was not modified");
                }

            } else {
                view.showMessage("The passenger doesn't exist");
            }

        }
    }

    /**
     * Method that lists a passenger by a flight
     */
    private void listPassengersByFlight() {
        String code = view.inputString("Input code of flight: ");
        if (code != null) {
            long idFlight = model.searchFlightId(code);
            Flight flight = model.searchFlightById(idFlight);
            if (flight != null) {
                List<FlightPassenger> fp = model.listPasengerByFlight(flight.getId());
                int cont = 0;
                if (fp != null) {
                    List<Passenger>passengers= new ArrayList<>();
                    Passenger p=null;
                    for (cont = 0; cont < fp.size(); cont++) {
                        p= model.searchPassengerById(fp.get(cont).getPassengerId());
                       if(p!=null){
                       passengers.add(p);
                       }
                    }
                    view.showPassengerList(passengers);
                } else {
                    System.out.println("Error retrieving data");
                }
            } else {
                view.showMessage("Flight not founded in the database");
            }
        } else {
            view.showMessage("The code given is not allowed");
        }

    }

    private void registerPassengerToFlight() {
        String phone = view.inputString("Input the phone of the passenger: ");
        if (phone != null) {
            long idPassenger = model.searchPassengerId(phone);
            Passenger passenger = model.searchPassengerById(idPassenger);
            if (passenger != null) {
                view.showMessage(passenger.toString());
                boolean confr = view.confirmation("Is this the passenger you were searching? (Y/N)");
                if (confr) {
                    String code = view.inputString("Well, now input the code of the flight");
                    if (code != null) {
                        long idFlight = model.searchFlightId(code);
                        Flight flight = model.searchFlightById(idFlight);
                        if (flight != null) {
                            view.showMessage(flight.toString());
                            boolean confirm = view.confirmation("Is this the flight where you want to resgister? (Y/N)");
                            if (confirm) {
                                if (flight.getCapacity() > 0) {
                                    FlightPassenger fp = new FlightPassenger(idFlight, idPassenger);
                                    int duplicated = model.duplicated(fp);
                                    if (duplicated == 0) {
                                        int x = model.registerPassengerToFlight(fp);
                                        if (x == 1) {
                                            System.out.println("Succesfully added");
                                        } else {
                                            view.showMessage("Passenger not addded");
                                        }
                                    } else {
                                        view.showMessage("This passenger is already in this flight");
                                    }
                                } else {
                                    view.showMessage("There is no space in the flight");
                                }
                            } else {
                                view.showMessage("Passenger not added");
                            }
                        } else {
                            view.showMessage("Flight not founded in the database");
                        }
                    } else {
                        view.showMessage("The code given is not allowed");
                    }
                } else {
                    view.showMessage("Passenger not added");
                }
            } else {
                view.showMessage("This passenger doesn't exist in to the database");
            }
        } else {
            view.showMessage("Error at input passenger");
        }
    }

    private void unregisterPassengerFromFlight() {
        String phone = view.inputString("Input phone of the passenger");
        if (phone != null) {
            long idPassenger = model.searchPassengerId(phone);
            Passenger passenger = model.searchPassengerById(idPassenger);
            if (passenger != null) {
                view.showMessage(passenger.toString());
                boolean confr = view.confirmation("Is this the passenger you were searching? (Y/N)");
                if (confr) {
                    view.showMessage("Passenger founded in the database");
                    String code = view.inputString("Well, now input the code of the flight");
                    if (code != null) {
                        long idFlight = model.searchFlightId(code);
                        Flight flight = model.searchFlightById(idFlight);
                        if (flight != null) {
                            view.showMessage(flight.toString());
                            boolean confirm = view.confirmation("Is this the flight where you want to resgister? (Y/N)");
                            if (confirm) {
                                long time = flight.getTime().getTime();
                                long timeNow = System.currentTimeMillis();
                                long differenceMinutes = (time - timeNow) / (1000 * 60);
                                if (differenceMinutes < 60) {
                                    view.showMessage("Well you're still able to cancel you flight ");
                                    FlightPassenger fp = new FlightPassenger(idFlight, idPassenger);
                                    int duplicated = model.duplicated(fp);
                                    System.out.println(fp.toString());
                                    if (duplicated != 0) {
                                        int x = model.unregisterPassengerFromFlight(fp);
                                        if (x != 0) {
                                            view.showMessage("Passenger succesfully unregistered from flight");
                                        } else {
                                            view.showMessage("Passenger not unregistered from flight");
                                        }
                                    } else {
                                        view.showMessage("This passenger is not in this flight");
                                    }
                                } else {
                                    view.showMessage("It's not posible to unregister if the flight is going to take off in less than an hour");
                                }
                            } else {
                                view.showMessage("Flight not founded in the database");
                            }
                        } else {
                            view.showMessage("Passeger is already registered");

                        }
                    } else {
                        view.showMessage("The code given is not allowed");
                    }
                } else {
                    view.showMessage("Passeger is already registered");
                }
            } else {
                view.showMessage("This passenger doesn't exist in to the database");
            }
        } else {
            view.showMessage("The phone is not well introduced");
        }
    }

}

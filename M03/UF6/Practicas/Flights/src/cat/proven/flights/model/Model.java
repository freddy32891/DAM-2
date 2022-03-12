/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.flights.model;

import cat.proven.flights.model.persist.FlightDao;
import cat.proven.flights.model.persist.FlightPassengerDao;
import cat.proven.flights.model.persist.PassengerDao;
import java.util.List;

/**
 * Model service for flight application
 *
 * @author fredd
 */
public class Model {

    private FlightDao flightDao;
    private PassengerDao passengerDao;
    private FlightPassengerDao flightPassengerDao;

    public Model() throws ClassNotFoundException {
        this.flightDao = new FlightDao();
        this.passengerDao = new PassengerDao();
        this.flightPassengerDao= new FlightPassengerDao();
    }

    /**
     * Method that calls the Dao to select all the flights
     * 
     * @return the a list with all the selected flies or null in case of error
     */
    public List<Flight> searchAllFlighhts() {
        List<Flight> result;
        result = flightDao.selectAll();
        return result;
    }

    /**
     * Method that calls the Dao to add a flight in the data
     * @param flight to add
     * @return 1 if succesfully added or 0 in case of error
     */
    public int addFlight(Flight flight) {
        return flightDao.insert(flight);
    }
    
    /**
     * Method that calls the dao to update a flight
     * @param flight given
     * @return 1 if sucesfully updated, 0 or negative number in case of error
     */
    public int updateFlight(Flight flight) {
        return flightDao.update(flight);
    }

    /**
     * Method that calls the Dao to remove a flight
     * @param flight to remove
     * @return 1 if succesfully removed  or 0 in case of error
     */
    public int removeFlight(Flight flight) {
        return flightDao.delete(flight);
    }

    /**
     * Method that calls the dao to search all the passengers
     * @return all the passangers in the database or null in case of error
     */
    public List<Passenger> searchAllPassengers() {
        List<Passenger> result = null;
        result = passengerDao.selectAll();
        return result;
    }

    /**
     * Method that calls the dao to add a passenger
     * @param passenger to add
     * @return 1 if succesfully added, 0 or negative number in case of error
     */
    public int addPassenger(Passenger passenger) {
        return passengerDao.insert(passenger);
    }

    /**
     * Method that calls the dao to update a passenger
     * @param passenger with the modifications
     * @return 1 if sucesfully updated, 0 or negative number in case of error
     */
    public int updatePassenger(Passenger passenger){
        return passengerDao.update(passenger);
    }
    /**
     * Method that calls the dao to remove a passenger
     * @param passenger to remove
     * @return 1 if sucesfully removed, 0 or negative number in case of error
     */
    public int removePassenger(Passenger passenger) {
        return passengerDao.remove(passenger);
    }
    
    /**
     * Method that calls the dao to find a flight by code
     * @param code the code of the flight
     * @return the flight or null in case of error
     */
    public Flight searchFlightByCode(String code){
        return flightDao.selectWhereCode(code);
    }

    /**
     * Method that calls the dao to find a passenger by phone
     * @param phone of the flight
     * @return 1 if sucesfully updated, 0 or negative number in case of error
     */
    public Passenger searchPassengerByPhone(String phone) {
        return passengerDao.selectWherePhone(phone);
    }

    /**
     * Method that callls the dao to search the flight by the given code
     * @param code of the flight
     * @return -1 in case of error or the id of the number
     */
    public long searchFlightId(String code) {
        return flightDao.selectFlightId(code);
    }

    /**
     * Method that calls the dao to search a flight by id
     * @param id of the flight
     * @return the flight or null in case of error
     */
    public Flight searchFlightById(long id){
        return flightDao.selectWhereId(id);
    }

    /**
     * Method that calls the dao to search the passenger id by a given phone
     * @param phone of the given passenger
     * @return 1 if sucesfully updated, 0 or negative number in case of error
     */
    public long searchPassengerId(String phone) {
        return passengerDao.searchPassengerId(phone);
    }

    /**
     * Method that calls the dao to search a Passenger by id
     * @param id of the passenger
     * @return the passenger or null in case of error
     */
    public Passenger searchPassengerById(long id) {
        return passengerDao.selectWhereId(id);
    }

    /**
     * Method that calls the dao to list a passenger by a flight
     * @param id id o a flight
     * @return a list of flightpassenger or null in case of error
     */
    public List<FlightPassenger> listPasengerByFlight(long id) {
        return flightPassengerDao.selectWhereFlightId(id);
    }

    /**
     * Method that calls the dao to register a passenger in a flight
     * @param passenger to add
     * @return 1 if sucesfully updated, 0 or negative number in case of error
     */
    public int registerPassengerToFlight(FlightPassenger passenger) {
        return flightPassengerDao.insert(passenger);
    }

    /**
     * Method that calls deletes a given passenger from a given flight
     * @param fp the flightPassengers
     * @return 1 if sucesfully deleted or 0 in case of error
     */
    public int unregisterPassengerFromFlight(FlightPassenger fp) {
        return flightPassengerDao.delete(fp);
    }

    /**
     * Method that calls the dao to ask if a flight passenger exists
     * @param fp flightpassenger
     * @return 1 if exists, 0 if not
     */
    public int duplicated(FlightPassenger fp) {
        return flightPassengerDao.duplicated(fp);
    }


}

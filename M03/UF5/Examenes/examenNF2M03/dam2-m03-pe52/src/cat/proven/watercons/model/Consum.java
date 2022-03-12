/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.watercons.model;

/**
 *
 * @author mati
 */
public class Consum {

    /**
     * Method that calculates the amount of water drinked by a person in a day
     *
     * @param litersPerMonth the amount of water drinked in a month
     * @param persons the number of people that drink the amount of water
     * @return the amount of water drinked by a person in a day
     */
    public int calculate(Double litersPerMonth, double persons) {
        double result = litersPerMonth / 30 / persons;
        return (int) result;
    }

    /**
     * Method that classificates the level of water drinked by a person in a day
     *
     * @param litersPerMonth the litters drink in a month
     * @param persons number of people that drink tha amount of water
     * @return low if the result is under 100, medium if it is between 100 an
     * 106 an high if it's higher than 106
     */
    public String level(Double litersPerMonth, double persons) {
        String lvl;
        double result;
        result = calculate(litersPerMonth, persons);
        if (result < 100) {
            lvl = "LOW";
        } else if (result >= 100 && result <= 106) {
            lvl = "MEDIUM";
        } else {
            lvl = "HIGH";
        }
        return lvl;
    }

}

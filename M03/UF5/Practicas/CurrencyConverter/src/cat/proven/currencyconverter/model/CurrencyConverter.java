/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.currencyconverter.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author freddy
 */
public class CurrencyConverter {

    private List<Coin> coins;

    public List<Coin> getCoins() {
        return coins;
    }

    
    public CurrencyConverter() {
        this.coins = new ArrayList<>();
        loadTestData();
    }

    public Coin getCoinByType(String type){
        Coin coin = new Coin("USD", 1.0);
    
    for(int i =0; i<coins.size();i++){
            if(type.equals(coins.get(i).getType())){
            coin=coins.get(i);
            }
    }
    return coin;
    }
    public List<String> getCurrencyNames() {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < coins.size(); i++) {
            names.add(coins.get(i).getType());
        }
        return names;
    }
    
    public double change (String initCoin, String finalCoin, double amount){
       //WE establish USD coin by default; 
        Coin sourceCurr=new Coin("USD", 1.0);
        Coin targCurr=new Coin("USD", 1.0);
        
        for(int i =0; i<coins.size();i++){
            if(initCoin.equals(coins.get(i).getType())){
            sourceCurr=coins.get(i);
            }
            if(finalCoin.equals(coins.get(i).getType())){
            targCurr=coins.get(i);
            }
        
        }
        
        
        return value(sourceCurr,targCurr,amount);
    
    }
    /**
    * Method that converts the value of an amount of money in one divisa to another
    * @param sourceCurr
    * @param targCurr
    * @param amount
    * @return 
    */
    public double value(Coin sourceCurr, Coin targCurr, double amount) {
        return (amount*sourceCurr.getValue())/targCurr.getValue();
    }
    
    public double returnRatio(double initialCoin, double finalCoin){
    return finalCoin/initialCoin;
    }

    
    public void loadTestData() {
        coins.add(new Coin("USD", 1.0));
        coins.add(new Coin("EUR", 1.19));
        coins.add(new Coin("GBP", 1.33));
        coins.add(new Coin("YEN", 0.00959));
    }

    
}

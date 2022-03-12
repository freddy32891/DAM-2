package com.example.fruitappmorando.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    //Constant with fruit
    private static final String APPLE = "Manzana";
    private static final String PEAR = "Pera";
    private static final String BANANA = "Platano";

    private static final int A_PRICE= 2;
    private static final int B_PRICE= 5;
    private static final int P_PRICE= 4;



    ArrayList<Fruit> fruits;

    //constructors
    public Game() {
        fruits = new ArrayList<>();
    }

    public void initGame(){
        fruits.clear();

        Fruit f1= new Fruit(APPLE,A_PRICE,0);
        Fruit f2= new Fruit(BANANA,B_PRICE,0);
        Fruit f3= new Fruit(PEAR,P_PRICE,0);

        fruits.add(f1);
        fruits.add(f2);
        fruits.add(f3);
    }

    public Fruit getFruit(int index) {
        Fruit f = null;

        if (index < fruits.size()) {
            f = fruits.get(index);
        }
        return f;
    }


    private int sumFruits(List<Fruit> list) {
        //init sum = 0
        int sum = 0;

        for (Fruit f : list) {
            sum += f.cantidad;
        }
        return sum;
    }


}

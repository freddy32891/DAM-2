/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.storeap;

import cat.proven.storeap.model.Fridge;
import cat.proven.storeap.model.Microwave;
import cat.proven.storeap.model.Product;
import cat.proven.storeap.model.Tv;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author mati
 */
public class ProductForm {
    /**
     * reads data for a product
     * @param type the of product to input
     * @return a product with read data or null in case of error
     */
    public static Product inputProduct(String type){
    Product p = null;
    switch (type) {
        case "Tv":
            p = inputTv();
            break;
        case "Fridge":
            p= inputFridge();
            break;
        case "Microwave":
            p= inputMicrowave();
            break;
        default:
            p=null;
            break;
    }
    return p;
    }
    /**
     * 
     * Inputs Tv
     */
    private static Product inputTv() {
        Product p = null;
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.print("Code: ");
        String code = sc.next();
        System.out.print("Description: ");
        String description = sc.next();
        try {
            System.out.print("Price: ");
            double price = sc.nextDouble();
            System.out.print("Stock: ");
            int stock = sc.nextInt();
            System.out.print("Inches: ");
            int inches= sc.nextInt();
            p = new Tv(code, description, price, stock, inches);

        } catch (InputMismatchException e) {
    //System.out.println(e.getMessage)
            p = null;
        }
        return p;
    }

     /**
     *
     * Inputs Fridge
     */
    private static Product inputFridge() {
       Product p = null;
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.print("Code: ");
        String code = sc.next();
        System.out.print("Description: ");
        String description = sc.next();
        try {
            System.out.print("Price: ");
            double price = sc.nextDouble();
            System.out.print("Stock: ");
            int stock = sc.nextInt();
            System.out.print("Capacity: ");
            int capacity= sc.nextInt();
            System.out.print("noFrost: ");
            boolean noFrost= sc.nextBoolean();
            p = new Fridge(code, description, price, stock, capacity, noFrost);

        } catch (InputMismatchException e) {
    //System.out.println(e.getMessage)
            p = null;
        }
        return p;
    }
    
     /**
     * 
     * Inputs Microwave
     */
    private static Product inputMicrowave(){
    Product p= null;
     Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.print("Code: ");
        String code = sc.next();
        System.out.println("Description: ");
        String description = sc.next();
        try {
            System.out.print("Price: ");
            double price = sc.nextDouble();
            System.out.print("Stock: ");
            int stock = sc.nextInt();
            System.out.print("Power: ");
            int power = sc.nextInt();
            System.out.print("Capacity: ");
            int capacity= sc.nextInt();
            System.out.print("Grill: ");
            boolean grill= sc.nextBoolean();
            p = new Microwave(code, description, price, stock, power, capacity, grill);

        } catch (InputMismatchException e) {
    //System.out.println(e.getMessage)
            p = null;
        }
        return p;
    }
}

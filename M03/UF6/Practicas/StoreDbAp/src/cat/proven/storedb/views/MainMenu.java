/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.storedb.views;

/**
 *
 * @author mati
 */
public class MainMenu extends Menu {

    /**
     *
     * "exit", "listallproducts", "findproductbycode",
     * "listproductswithlowcost", "addproduct", "modifyproduct",
     * "removeproduct", "lisproducttype"
     *
     */

    public MainMenu() {
        setTitle("Product application with database");
        addOption(new Option("Exit","exit"));
        addOption(new Option( "List all products","listallproducts"));
        addOption(new Option("Find product by code","findproductbycode"));
        addOption(new Option("List products with low stock","listproductswithlowcost"));
        addOption(new Option("Add a new product","addproduct"));
        addOption(new Option("Modifiy a product","modifyproduct"));
        addOption(new Option("Remove a product","removeproduct"));
        addOption(new Option("List products by type","lisproducttype"));

    }
}

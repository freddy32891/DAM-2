/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.storeap.model;

import java.util.List;

/**
 *
 * @author fredd
 */
public interface StoreInterface {

    /**
     * Add a product to the date Store Avoids id duplicates, prevents null
    products and adding products over array capacity
     *
     * @param product the product to add
     * @return true if it's succesfully added, false if not Date 28-09-2020 to
     * do
     */
    boolean addProduct(Product product);

    /**
     * data product at position index
     *
     * @param index
     * @return the postion of the product
     */
    //    public Product get(int index) {
    //        Product p = null;
    //        p = products[index];
    //        return p;
    //        //solve problem required
    //    }
    //methods
    /**
     * Shows all products in the data Store
     *
     * @return store with all products or null 30-09-2020
     */
    List<Product> findAllProducts();

    /*
     * Search id with the given id
     * @param id
     * @return the product searched or null in case that there's an error
     */
    Product findProductByCode(String code);

    /**
     * Method that returns the position of the product given the id
     *
     * @param code
     * @return index of the given product or -1 in case of error
     */
    int findProductIndex(String code);

    List<Product> findProductsByType(String type);

    /**
     * Catch all the products with the stock asked
     *
     * @param stock
     * @return a list of products with the stock required
     */
    List<Product> findProductsWithLowStock(int stock);

    //Getters
    List<Product> getProducts();

    /**
     * Generates test data
     */
    void loadTestData();

    /**
     * Modifies a product given 2 products avoiding null cases
     *
     * @param newProduct
     * @param oldProduct
     * @return true if the modification was succesfully, if not, false
     */
    boolean modifyProduct(Product newProduct, Product oldProduct);

    /**
     * REmove the product by id.Avoiding duplicate id
     *
     * @param p Product to remvoe
     * @return
     */
    boolean removeProduct(Product p);
    
}

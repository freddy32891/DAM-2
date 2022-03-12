/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.storeap.model;

import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author fredd
 */
public class StoreTest {

    public StoreTest() {
    }

    /**
     * Test of getProducts method, of class Store.
     */
    @Test
    public void testGetProducts() {
        System.out.println("getProducts");
        Store instance = new Store();
        List<Product> expResult = new ArrayList<>();
        expResult.add(new Tv("TV1", "Television LED 4K with 50 inches", 4394.0, 4, 40));
        expResult.add(new Fridge("FR1", "Fridge 220L noFrost", 1200.0, 3, 220, true));
        expResult.add(new Tv("TV2", "Television Plasma 52 inches", 500.0, 8, 52));
        List<Product> result = instance.getProducts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findAllProducts method, of class Store.
     */
    @Test
    public void testFindAllProducts() {
        System.out.println("findAllProducts");
        Store instance = new Store();
        List<Product> expResult = new ArrayList<>();
        expResult.add(new Tv("TV1", "Television LED 4K with 50 inches", 4394.0, 4, 40));
        expResult.add(new Fridge("FR1", "Fridge 220L noFrost", 1200.0, 3, 220, true));
        expResult.add(new Tv("TV2", "Television Plasma 52 inches", 500.0, 8, 52));
        List<Product> result = instance.findAllProducts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of removeProduct method, of class Store.
     */
    @Test
    public void testRemoveProduct() {
        System.out.println("removeProduct");
        Product p = new Tv("TV1", "Television LED 4K with 50 inches", 4394.0, 4, 40);
        Store instance = new Store();
        boolean expResult = true;
        boolean result = instance.removeProduct(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findProductIndex method, of class Store.
     */
    @Test
    public void testFindProductIndex() {
        System.out.println("findProductIndex");
        String code = "TV1";
        Store instance = new Store();
        int expResult = 0;
        int result = instance.findProductIndex(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of modifyProduct method, of class Store.
     */
    @Test
    public void testModifyProduct() {
        System.out.println("modifyProduct");
        Product newProduct = new Tv("TV10", "Television LED 4K with 90 inches", 4844.0, 6, 80);
        Product oldProduct = new Tv("TV1", "Television LED 4K with 50 inches", 4394.0, 4, 40);
        Store instance = new Store();
        boolean expResult = true;
        boolean result = instance.modifyProduct(newProduct, oldProduct);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findProductByCode method, of class Store.
     */
    @Test
    public void testFindProductByCode() {
        System.out.println("findProductByCode");
        String code = "TV1";
        Store instance = new Store();
        Product expResult = new Tv("TV1", "Television LED 4K with 50 inches", 4394.0, 4, 40);
        Product result = instance.findProductByCode(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of addProduct method, of class Store.
     */
    @Test
    public void testAddProduct() {
        System.out.println("addProduct");
        Product product = new Tv("TV4", "Television full HD with 80 inches", 4344.0, 2, 60);
        Store instance = new Store();
        boolean expResult = true;
        boolean result = instance.addProduct(product);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of findProductsWithLowStock method, of class Store.
     */
    @Test
    public void testFindProductsWithLowStock() {
        System.out.println("findProductsWithLowStock");
        int stock = 5;
        Store instance = new Store();
        List<Product> expResult = new ArrayList<>();
        expResult.add(new Tv("TV2", "Television Plasma 52 inches", 500.0, 8, 52));
        List<Product> result = instance.findProductsWithLowStock(stock);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findProductsByType method, of class Store.
     */
    @Test
    public void testFindProductsByType() {
        System.out.println("findProductsByType");
        String type = "Fridge";
        Store instance = new Store();
        List<Product> expResult = new ArrayList<>();
        expResult.add(new Fridge("FR1", "Fridge 220L noFrost", 1200.0, 3, 220, true));
        List<Product> result = instance.findProductsByType(type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of loadTestData method, of class Store.
     */
    @Test
    public void testLoadTestData() {
        System.out.println("loadTestData");
        Store instance = new Store();
        instance.loadTestData();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}

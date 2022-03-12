/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.storeap;

import cat.proven.storeap.model.Product;
import cat.proven.storeap.model.Store;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * Date: 19-09-2020
 *
 * @author FreddySoft
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private Store myStore;
    private Properties i18nProperties;
    private static final String CONFIG_FILE_BASE_NAME = "resources/store_cfg";
    private static final String PROPS_FILE_EXT = "properties";
    private Properties configProperties = new Properties();
//    private String[] mainMenu = {
//        "Exit", "List all products", "Find product by code", "List products with low cost", "Add new product", "Modify product", "Remove product"
//    };
    private String[] mainMenu = {
        "exit", "listallproducts", "findproductbycode", "listproductswithlowcost", "addproduct", "modifyproduct", "removeproduct", "lisproducttype"
    };

    public static void main(String[] args) {
        // TODO code application logic here
        Main app = new Main();
        app.run();
    }

    private void run() {
        boolean exit = false; //flag to exit program.
        //create a Store
        myStore = new Store();
        //load Data
        myStore.loadTestData();
        String language = inputString("Input a language (spanish, catalan, english) ");
        do {
            try {
                i18nProperties = initProperties(language.toLowerCase());

                //show menu
                int selectedOption = showMenuAndReadOption();

                //process action
                switch (selectedOption) {
                    case 0: //Exit
                        exit = true;
                        break;
                    case 1: //List all products
                        findAllProducts();
                        break;
                    case 2: //Find product by code
                        findProductByCode();
                        break;
                    case 3: //List products with low cost
                        listProductsWithLowStock();
                        break;
                    case 4: //Add new product
                        addNewProduct();
                        break;
                    case 5: //Modify product
                        modifyProduct();
                        break;
                    case 6: //Remove product
                        removeProduct();
                        break;
                    case 7:
                        listProductsByType();
                        break;
                    default:
                        System.out.println(translate("invalidoption"));
                        break;
                }

            } catch (IOException ex) {
                System.out.println("Language not founded, it will run in English by default.");
                language = "english";
            }
        } while (!exit);
        System.out.println(translate("goodbye"));
    }

    /**
     * displays main menu and reads option from user.
     *
     * @return number of option selected by user or -1 in case of an error.
     */
    private int showMenuAndReadOption() {
        int option = -1;
        for (int i = 0; i < mainMenu.length; i++) {
            System.out.format("%d. %s\n", i, translate(mainMenu[i]));
        }
        //read option.
        Scanner myscan = new Scanner(System.in);
        try {
            System.out.print(translate("selectanoption"));
            option = myscan.nextInt();
        } catch (InputMismatchException e) {
            //System.out.println(e.getMessage());
            option = -1;
        }
        return option;
    }
    //Control methods

    /**
     * Shows all products in the data Store
     *
     * @returns store with all products or null 30-09-2020 if data is null, it's
     * reported
     */
    private void findAllProducts() {

        //
        List<Product> data = myStore.findAllProducts();
        if (data != null) {
            for (Product obj : data) {
                System.out.println(obj.toString());
            }
        } else {
            System.out.println(translate("errorretrievingdata"));
        }
        //display data to User
    }

    /**
     * Show product by code Asks the user a code, if it's correctly read, it
     * searches a product with the same code If found , it shows the product to
     * the user. If not found, it shows an error message
     *
     */
    private void findProductByCode() {
        //read the code
        String code = inputString(translate("inputcode"));

        if (code != null) {//search code
            Product found = myStore.findProductByCode(code);
            if (found != null) {
                displayProduct(found);
            } else { //not found
                System.out.println(translate("productnotfound"));
            }
        } else {
            System.out.println(translate("errorreadingcode"));
        }
    }

    /**
     * Ask the user which type of product to add. Shows a dorm to the user to
     * input new product data if an error reading the data, report the error to
     * the user if correct read, add the product to the data store
     */
    private void addNewProduct() {
        //Ask or read  type of product
        String productType = inputString(translate("typeproduct"));
        if (productType == null) {
            System.out.println(translate("errorreadingtype"));
        } else {
            //Read product from user
            Product newP = ProductForm.inputProduct(productType);

            if (newP != null) {
                //add Product to the data Store
                boolean result = myStore.addProduct(newP);
                if (result) {
                    System.out.println(translate("productsuccesfullyadded"));
                } else {
                    System.out.println(translate("erroraddingproduct"));
                }
            } else {
                System.out.println(translate("errorreadingproduct"));
            }
        }
    }

    /**
     * NOT USEFUL Aks the user to input for a new product
     *
     * @ return a product with the data or null in case of error if correct
     * read, add the product to the data store
     */
//    private Product inputProduct() {
//        Product p = null;
//        String code = inputString("Code: ");
//        String description = inputString("Description: ");
//        try {
//            String sPrice = inputString("Price: ");
//            double price = Double.parseDouble(sPrice);
//            String sStock = inputString("Stock: ");
//            int stock = Integer.parseInt(sStock);
//            p = new Product(code, description, price, stock);
//
//        } catch (NumberFormatException e) {
//    //System.out.println(e.getMessage)
//            p = null;
//        }
//        return p;
//    }
    /**
     * Asks the code of th product and Removes the product searched by code in
     * case that the code is null it's reported in case that there's no the
     * prodcut with that code, it's reported in case hat there's an error
     * removing the product, it's reported
     */
    private void removeProduct() {
        String code = inputString(translate("inputcode"));
        if (code != null) {
            Product p = myStore.findProductByCode(code);
            if (p != null) {
                displayProduct(p);
                String option = inputString(translate("areyousure"));
                if (option.equalsIgnoreCase("Y")) {
                    boolean removed = myStore.removeProduct(p);
                    if (removed) {
                        System.out.println(translate("productremoved"));
                    } else {
                        System.out.println(translate("errorremoving"));
                    }
                } else if (option.equalsIgnoreCase("N")) {
                    System.out.println(translate("productnotremoved"));
                } else {
                    System.out.println(translate("invalidoption"));
                }

            } else {
                System.out.println(translate("productnotfound"));
            }

        } else {
            System.out.println(translate("errorreadingcode"));
        }

    }

    /**
     * Ask te user the code of the product and Modifies the product searched by
     * code from the data store if the code is null, reports the user with
     * message if the prodct does not exist reports to user if its not correctly
     * modified report to user
     */
    private void modifyProduct() {
        String code = inputString(translate("inputcode"));
        if (code != null) {
            Product found = myStore.findProductByCode(code);
            if (found != null) {
                displayProduct(found);
                String option = inputString(translate("areyousure"));
                if (option.equalsIgnoreCase("Y")) {
                    System.out.println(translate("inputdata"));
                    if (found.getClass().getSimpleName().equals("Fridge")) {
                        Product newP = ProductForm.inputProduct("Fridge");
                        if (newP != null) {
                            boolean modify = myStore.modifyProduct(newP, found);
                            if (modify) {
                                System.out.println(translate("productmodified"));
                            } else {
                                System.out.println(translate("errormodify"));
                            }
                        } else {
                            System.out.println(translate("productnull"));
                        }
                    } else if (found.getClass().getSimpleName().equals("Microwave")) {
                        Product newP = ProductForm.inputProduct("Microwave");
                        if (newP != null) {
                            boolean modify = myStore.modifyProduct(newP, found);
                            if (modify) {
                                System.out.println(translate("productmodified"));
                            } else {
                                System.out.println(translate("errormodify"));
                            }
                        } else {
                            System.out.println(translate("productnull"));
                        }
                    } else if (found.getClass().getSimpleName().equals("Tv")) {
                        Product newP = ProductForm.inputProduct("Tv");
                        if (newP != null) {
                            boolean modify = myStore.modifyProduct(newP, found);
                            if (modify) {
                                System.out.println(translate("productmodified"));
                            } else {
                                System.out.println(translate("errormodify"));
                            }
                        } else {
                            System.out.println(translate("productnull"));
                        }
                    }

                } else {
                    System.out.println(translate("productnotmodified"));
                }

            } else {
                System.out.println(translate("productnotfound"));
            }
        } else {
            System.out.println(translate("errorreadingcode"));
        }
    }

    /**
     * Ask the user the minimum stock required List products with the minimum
     * stock asked if theres an error reading the stock, it's reported if the
     * list is empty, it's reported
     */
    private void listProductsWithLowStock() {
        int stock = inputNumber(translate("numberstock"));
        List<Product> lowStock = myStore.findProductsWithLowStock(stock);
        if (lowStock != null) {
            displayProductTable(lowStock);
        } else {
            System.out.println(translate("productsnotfounded"));
        }
    }

    /**
     * Ask the user the type of the product he wants to search. List all
     * Products with the given type if list is empty shows a message reporting
     * it
     */
    private void listProductsByType() {
        String type = inputString(translate("inputype"));
        if (type != null) {
            List<Product> listType = myStore.findProductsByType(type);
            if (listType != null) {
                displayProductTable(listType);
            } else {
                System.out.println(translate("productsnotfounded"));
            }
        } else {
            System.out.println(translate("errorreadingtype"));
        }
    }

    /**
     * Asks to user the number of minimum stock.
     *
     * @return the number of minStock
     */
    private int inputNumber(String message) {
        Scanner sc = new Scanner(System.in);
        int number = 0;
        System.out.print(message);
        try {
            number = sc.nextInt();
        } catch (InputMismatchException e) {
            number=-1;
        }
        return number;
    }

    //view methods
    /**
     * Presents a message to user and read an answer
     *
     * @param message
     * @return the answer of user or an error if it's incorrect Date 28-09-2020
     */
    private String inputString(String message) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        return sc.next();
    }

    /**
     * Displays a product to User
     *
     * @param product 28-09-2020f
     */
    private void displayProduct(Product product) {
        System.out.println(product.toString());
    }

    /**
     * Displays a List of all products required
     *
     * @returns data to display 30-09-2020
     */
    private void displayProductTable(List<Product> data) {
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i).toString());
        }
    }

    /**
     * Translates a mesasge by the key
     *
     * @param key
     * @return an String with the translation
     */
    private String translate(String key) {
        return i18nProperties.getProperty(key);
    }

    /**
     * open translations files to modified the text to another languages
     *
     * @return propertis with the choose language
     */
    private Properties initProperties(String option) throws IOException {
        configProperties.load(new FileInputStream(CONFIG_FILE_BASE_NAME + "." + PROPS_FILE_EXT));
        String idLanguage = configProperties.getProperty(option);
        configProperties.load(new FileInputStream("resources/i18n/" + idLanguage + "_store.properties"));
        return configProperties;
    }

}

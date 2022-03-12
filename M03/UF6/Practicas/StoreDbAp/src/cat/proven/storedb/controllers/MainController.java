package cat.proven.storedb.controllers;

import cat.proven.storedb.model.Model;
import cat.proven.storedb.model.Product;
import cat.proven.storedb.views.MainView;
import java.util.List;

/**
 *
 * @author mati
 */
public class MainController {

    private final Model model;
    private final MainView view;

    public Model getModel() {
        return model;
    }

    public MainView getView() {
        return view;
    }

    public MainController(Model model) {
        this.model = model;
        this.view = new MainView(this);

    }

    public void processAction(String action) {
        switch (action) {
            case "exit":
                exitApplication();
                break;
            case "listallproducts":
                listAllProducts();
                break;
            case "findproductbycode":
                findProductByCode();
                break;
            case "listproductswithlowcost":
                listProductsWithLowStock();
                break;
            case "addproduct":
                addProduct();
                break;
            case "modifyproduct":
                modifyProduct();
                break;
            case "removeproduct":
                removeProduct();
                break;
            case "lisproducttype":

                break;
            default:
                break;
        }
    }

    private void exitApplication() {
        view.setExit(true);
    }

    private void listAllProducts() {
        List<Product> data = model.searchAllProducts();
        if (data != null) {
            view.displayProductTable(data);

        } else {
            view.displayMessage("Error retrieving data");
        }
    }

    private void findProductByCode() {
        String code = view.inputString("Input code:");
        if (code != null) {
            Product p = model.searchProductByCode(code);
            if (p != null) {
                view.displayProduct(p);
            }else{
            view.displayMessage("Product not founded :(");
            }
        } else {
            view.displayMessage("Error reading code");
        }
    }
    
    private void listProductsWithLowStock(){
        int stock= view.askForLowStock("Input the minimum number of stock");
        List <Product> data=model.searchProductsWithLowStock();
        if (data != null) {
            view.displayProductTable(data);
        } else {
            view.displayMessage("Error retrieving data");
        }
    }   
    
    private void addProduct(){
        Product p= view.inputProduct();
        if(p!=null){
        int result=model.addProduct(p);
        if(result==1){
            view.displayMessage("Product succesfully added");
        }else{
            view.displayMessage("Product not added");           
        }
        }else{
        view.displayMessage("There's an error when input the product");
        }
    }
    
    private void removeProduct(){
    String code=view.InputSTring("Input code");
    if(code!=null){
        Product p= model.searchProductByCode(code);
        if(p!=null){
        int result=model.removeProduct(p);
        if(result==1){
            view.displayMessage("Product succesfully removed");
        }else{
            view.displayMessage("Product not removed");
        }
        }else{
            view.displayMessage("Product not founded");
        }
    }else{
        view.displayMessage("Error reading code");
    }
    }

    private void modifyProduct() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    

}

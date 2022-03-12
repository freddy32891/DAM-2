package cat.proven.storeap.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates collection of products
 *
 * @author FreddySoft Date 28/09/2020
 */
public class Store implements StoreInterface {

    //private Product[] products; //array of products
    private List<Product> products = new ArrayList<>();

    //Constructors
    public Store() {
        this.products = new ArrayList<>();
        //loadTestData();//Remove introduction
    }

    //Getters
    @Override
    public List <Product> getProducts() {
        return products;
    }


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
    @Override
    public List<Product> findAllProducts() {
        return products;
    }

    /**
     * REmove the product by id.Avoiding duplicate id
     *
     * @param p Product to remvoe
     * @return
     */
    @Override
    public boolean removeProduct(Product p) {
        boolean correctlyRemoved = false;
        if (p != null) {
            int index = findProductIndex(p.getId());
            if (index != -1) {
                products.remove(p);
                correctlyRemoved = true;
            }else{
            correctlyRemoved=false;
            }
        }else{
        correctlyRemoved=false;
        }

        return correctlyRemoved;
    }

    /**
     * Method that returns the position of the product given the id
     * 
     * @param code
     * @return index of the given product or -1 in case of error
     */
    @Override
    public int findProductIndex(String code) {
        int position = -1;
        Product p= new Product(code);
            if (products.contains(p)) {
            position = products.indexOf(p);
        }
        System.out.println(position);
        return position;
    }

    /**
     * Modifies a product given 2 products avoiding null cases
     *
     * @param newProduct
     * @param oldProduct
     * @return true if the modification was succesfully, if not, false
     */
    @Override
    public boolean modifyProduct(Product newProduct, Product oldProduct) {
        boolean b = false;
        if (newProduct != null && oldProduct != null) {
            if (!products.contains(oldProduct)) {
                b = false;
            } else {
                products.set(findProductIndex(oldProduct.getId()), newProduct);
                b = true;
            }
        } else {
            b = false;
        }         

        return b;
    }

    /*
    * Search id with the given id
    * @param id
    * @return the product searched or null in case that there's an error 
     */
    @Override
    public Product findProductByCode(String code) {
        Product found = null;
//        for (int i = 0; i < products.size(); i++) {
//            Product p = products.get(i);
//            if (p.getCode().equals(id)) {
//                found = p;
//                break;
//            }
//        }
        int index = products.indexOf(new Product(code)); //returns the index of the product
        if (index >= 0) {
            found = products.get(index);
        } else {
            found = null;
        }

        return found;
    }

    /**
     * Add a product to the date Store Avoids id duplicates, prevents null
 products and adding products over array capacity
     *
     * @param product the product to add
     * @return true if it's succesfully added, false if not Date 28-09-2020 to
     * do
     */
    @Override
    public boolean addProduct(Product product) {
        boolean b = false;
//        if (product != null) {
//            if (numProducts >= capacity) {
//                b = false;
//            } else {
//                if (product.getCode() == null) {
//                    b = false;
//                } else {
//                    Product p = findProductByCode(product.getCode());
//                    if (p != null) { //product exists.
//                        b = false;
//                    } else {
//                        products[numProducts] = product;
//                        numProducts++;
//                        b = true;
//                    }
//                }
//            }
//        } else {
//            b = false;
//        }

        if (product != null) {
            if (!products.contains(product)) {
                b=products.add(product);
            } else {
                b = false;

            }

        } else {
            b = false;

        }
        return b;
    }

    /**
     * Catch all the products with the stock asked
     *
     * @param stock
     * @return a list of products with the stock required
     */
    @Override
    public List<Product> findProductsWithLowStock(int stock) {
        List<Product>lowStock=new ArrayList();
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.getStock() >= stock) {
                lowStock.add(p);
            }
        }
        return lowStock;
    }
    
    @Override
    public List<Product> findProductsByType(String type){
        List<Product>listType=new ArrayList();
       for (Product p : products) {
       if(type.equals(p.getClass().getSimpleName())){
       listType.add(p);
       }
           
       }
    return listType;
    }

    /**
     * Generates test data
     */
    @Override
    public void loadTestData() {
        addProduct(new Tv("TV1", "Television LED 4K with 50 inches", 4394.0, 4, 40));
        addProduct(new Fridge("FR1", "Fridge 220L noFrost", 1200.0, 3, 220, true));
        addProduct(new Tv("TV2", "Television Plasma 52 inches", 500.0, 8, 52));
    }


}

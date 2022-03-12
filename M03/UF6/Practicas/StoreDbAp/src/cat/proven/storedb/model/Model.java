package cat.proven.storedb.model;

import cat.proven.storedb.model.persist.ProductDAO;
import java.util.List;

/**
 *
 * @author mati
 */
public class Model {

    private final ProductDAO productDao;

    public Model(){
    this.productDao=new ProductDAO();
    }
    /**
     * Method that searches all the product in the database
     */
    public List<Product> searchAllProducts() {
        List<Product> result;
        result=productDao.selectAll();
        return result;
    }

    /**
     * Method that searchs a code with the given code
     *
     * @param code to search
     * @return a product with the given code or null in case of error or not
     * found
     */
    public Product searchProductByCode(String code) {
        Product result = null;
        result=productDao.findByCode(code);
        return result;
    }
    /**
     * Method that adds a product to the database. Prevents duplicate code
     * @param product the product to add
     * @return 1 if succesfully, 0 otherwise.
     */
    public int addProduct(Product product){
    int result = 0;
    //TODO
        return result;
    }

    public List<Product> searchProductsWithLowStock() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

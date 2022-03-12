/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.storedb.model.persist;

import cat.proven.storedb.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mati
 */
public class ProductDAO {

    private DbConnect dbConnect;
    private final String QUERY_SELECT_ALL = "select * from products";
    private final String QUERY_SELECT_BY_CODE="select * from products where code = code10";

    public ProductDAO() {
        try {
            dbConnect = new DbConnect();
            dbConnect.loadDriver();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Converts result set entry to product
     *
     * @param rs result set to get data from
     * @return product with data in current position of resultset
     */
    private Product fromResultSet(ResultSet rs) throws SQLException {
        Product p = null;
        int id = rs.getInt("id");
        String code = rs.getString("code");
        String description = rs.getString("description");
        double price = rs.getDouble("price");
        int stock = rs.getInt("stock");
        p = new Product(id, code, description, price, stock);
        return p;
    }

    /**
     * SElects all products from database 
     * @return list of products or null in case of error
     */
    public List<Product> selectAll() {
        List<Product> result = new ArrayList<>();
        try {
            Connection connection = dbConnect.getConnection();
            if (connection != null) {
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(QUERY_SELECT_ALL);
                while (rs.next()) {
                    Product p = fromResultSet(rs);
                    if (p != null) {
                        result.add(p);
                    }
                }
            }
        } catch (SQLException ex) {
            //Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = null;
        }
        return result;
    }

    public Product findByCode(String code) {
        Product result = null;
        try {
            Connection connection = dbConnect.getConnection();
            if (connection != null) {
                PreparedStatement st = connection.prepareStatement(QUERY_SELECT_BY_CODE);
                st.setString(1, code);
                ResultSet rs = st.executeQuery();
                boolean success = rs.next();
                if (!success) throw new RuntimeException("Fila no existent");
                result = fromResultSet(rs);
            }
        } catch (SQLException e) {
            result=null;
        }
        return result;
    }
    

}

package repository;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface DBConnection {
    List<Product> getAllProducts() throws SQLException;
}

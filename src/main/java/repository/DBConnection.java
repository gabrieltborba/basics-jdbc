package repository;

import model.Products;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DBConnection {
    List<Products> getAllProducts() throws SQLException;
}

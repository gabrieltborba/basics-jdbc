package services;

import model.Products;
import repository.MySqlConnector;

import java.sql.SQLException;
import java.util.List;

public class TestaConexão {
    public static void main(String[] args) throws SQLException {
        MySqlConnector mySqlConnector = new MySqlConnector();
        List<Products> products = mySqlConnector.getAllProducts();

        products.stream().forEach(prd -> System.out.println(prd.toString()));
    }
}
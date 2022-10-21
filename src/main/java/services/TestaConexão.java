package services;

import model.Product;
import repository.LocalConnection;

import java.sql.SQLException;
import java.util.List;

public class TestaConexão {
    public static void main(String[] args) throws SQLException {

        List<Product> products = LocalConnection.getInstance().getAllProducts();
        products.stream().forEach(product -> System.out.println(product.toString()));

        products = LocalConnection.getInstance().getAllProducts();
        products.stream().forEach(product -> System.out.println(product.toString()));
    }
}
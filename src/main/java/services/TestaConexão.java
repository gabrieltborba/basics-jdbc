package services;

import model.Product;
import repository.LocalConnection;

import java.sql.SQLException;
import java.util.List;

public class TestaConexão {
    public static void main(String[] args) throws SQLException {

        List<Product> products = LocalConnection.getInstance().getAllProducts();
        products.stream().forEach(product -> System.out.println(product.toString()));

        Product product = new Product("GELADEIRA","BRASTEMP PRETO DELUX");
        Product product1 = LocalConnection.getInstance().insertProduct(product);
        System.out.println("Produto add: " + product1.toString());

        products = LocalConnection.getInstance().getAllProducts();
        products.stream().forEach(prd -> System.out.println(prd.toString()));

        LocalConnection.getInstance().remove(7);
        LocalConnection.getInstance().remove(8);
        LocalConnection.getInstance().remove(9);

        products = LocalConnection.getInstance().getAllProducts();
        products.stream().forEach(prd -> System.out.println(prd.toString()));
    }
}
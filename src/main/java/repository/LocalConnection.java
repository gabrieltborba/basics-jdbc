package repository;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocalConnection implements DBConnection {

    private static LocalConnection instance;
    private static Connection connection;
    private LocalConnection() throws SQLException {
        connection = DriverManager
                .getConnection("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC","root","password");
    }
    public static LocalConnection getInstance() throws SQLException {
        if(instance == null)
            instance = new LocalConnection();
        return instance;
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {
        try {
            List<Product> products = new ArrayList<>();
            Statement statement = connection.createStatement();
            statement.execute("SELECT * FROM PRODUTO");
            ResultSet rst = statement.getResultSet();

            while (rst.next()) {
                Product product = new Product();
                product.setId(rst.getInt("ID"));
                product.setNome(rst.getString("NOME"));
                product.setDescricao(rst.getString("DESCRICAO"));
                products.add(product);
            }

            return products;
        } catch (Exception e) {
            e.printStackTrace();
            connection.close();
            return new ArrayList<>();
        }
    }
}

package repository;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocalConnection implements DBConnection {

    private static LocalConnection instance;
    private static Connection connection;
    private LocalConnection() throws SQLException {
        openConnection();
    }
    public static LocalConnection getInstance() throws SQLException {
        if(instance == null)
            instance = new LocalConnection();
        return instance;
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {
        try {
            if(connection.isClosed()){
                openConnection();
            }

            String query = "SELECT * FROM PRODUTO";
            List<Product> products = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
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
            return new ArrayList<>();
        }finally {
            connection.close();
        }
    }

    @Override
    public Product insertProduct(Product product) throws SQLException {
        try {
            if(connection.isClosed()){
              openConnection();
            }

            String query = "INSERT INTO PRODUTO (nome,descricao) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,product.getNome());
            statement.setString(2,product.getDescricao());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            int id = rs.next() ? rs.getInt(1) : 0;
            product.setId(id);

            return product;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            connection.close();
        }
    }

    @Override
    public Boolean remove(final int idProduct) throws SQLException {
        try {
            if(connection.isClosed()){
                openConnection();
            }

            String query = "DELETE FROM PRODUTO WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,idProduct);
            statement.executeUpdate();
            System.out.println("Removed with success Product with id: " + idProduct);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            connection.close();
        }
    }

    private void openConnection() throws SQLException {
        connection = DriverManager
                .getConnection("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC","root","password");
    }
}

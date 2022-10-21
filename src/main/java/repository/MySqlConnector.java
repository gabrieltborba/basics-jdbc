package repository;

import model.Products;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlConnector implements DBConnection {
    private Connection con;

    public MySqlConnector() throws SQLException {
        con = getInstance();
    }

    private Connection getInstance() throws SQLException {

        if(con == null)
            con = DriverManager
                .getConnection("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC","root","password");
        return con;
    }

    @Override
    public List<Products> getAllProducts() throws SQLException {

        List<Products> products = new ArrayList<>();
        Statement statement = con.createStatement();
        statement.execute("SELECT * FROM PRODUTO");
        ResultSet rst = statement.getResultSet();

        while(rst.next()){
            Products product = new Products();
            product.setId(rst.getInt("ID"));
            product.setNome(rst.getString("NOME"));
            product.setDescricao(rst.getString("DESCRICAO"));
            products.add(product);
        }
        con.close();

        return products;
    }
}

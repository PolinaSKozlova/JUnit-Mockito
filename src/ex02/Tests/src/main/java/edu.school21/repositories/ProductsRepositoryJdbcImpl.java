package edu.school21.repositories;

import models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private static int PRODUCTS_COUNT;
    private static Connection connection;
    private DataSource dataSource;


    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            final String request = "SELECT * FROM Person";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);

            while (resultSet.next()) {
                Product product = new Product(resultSet.getInt("book_id"),
                        resultSet.getString("book_name"),
                        resultSet.getFloat("book_price"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {
        final String request = "SELECT * FROM products WHERE id= ?";

        Product product = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product = new Product(
                        resultSet.getInt("book_id"),
                        resultSet.getString("book_name"),
                        resultSet.getFloat("book_price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (Optional.ofNullable(product));

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void save(Product product) {
        try {
            Statement statement = connection.createStatement();
            String SQL =
                    "INSERT INTO books VALUES(" + 1 + ",'" + product.getName() +
                            "'," + product.getName() + ",'"
                            + product.getPrice() + "')";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Statement statement = connection.createStatement();
            String SQL =
                    "DELETE FROM books WHERE book_id = " + id;
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

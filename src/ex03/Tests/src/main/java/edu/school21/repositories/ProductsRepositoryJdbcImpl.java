package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private static Connection connection;
    private DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            final String request = "SELECT * FROM books";
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
        Product product = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM books WHERE book_id=?");
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                product = new Product(
                        resultSet.getLong("book_id"),
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
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE books SET book_name=?, " +
                            "book_price=? WHERE book_id=?");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setFloat(2, product.getPrice());
            preparedStatement.setLong(3, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Product product) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO books VALUES(?, ?, ?)");

            preparedStatement.setLong(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setFloat(3, product.getPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM books WHERE book_id=?");

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package edu.school21.repositories;

import com.sun.jdi.connect.spi.Connection;
import org.apache.maven.plugins.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private static int PRODUCTS_COUNT;
    //    private List<Product> products;
    private static Connection connection;

//    public ProductsRepositoryJdbcImpl() {
//        products = new ArrayList<>();
//    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Product product = new Product(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"));
                products.add(person);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Statement statement = connection.createStatement();
            String SQL =
                    "DELETE FROM books WHERE id = " + id;
            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

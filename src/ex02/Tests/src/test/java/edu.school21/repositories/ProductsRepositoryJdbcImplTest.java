package edu.school21.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.school21.models.Product;
import org.junit.jupiter.api.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsRepositoryJdbcImplTest {
    private final List<Product> EXPECTED_FIND_ALL_PRODUCTS = new ArrayList<>();
    //    final Product EXPECTED_FIND_BY_ID_PRODUCT;
//    final Product EXPECTED_UPDATED_PRODUCT;
    private EmbeddedDatabase dataSource;

    @BeforeEach
    void init() throws SQLException {
        dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScripts("data.sql")
                .build();
    }

    @Test
    @DisplayName("Test for findAll method")
    public void testFindAll() {
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(1L, "Harry Potter and the " +
                "Philosophers Stone", 799.00F));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(2L,
                "Harry Potter and the Chamber of Secrets", 600.00F));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(3L,
                "Harry Potter and the Prisoner of Azkaban", 899.00F));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(4L,
                "Harry Potter and the Goblet of Fire", 799.00F));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(5L,
                "Harry Potter and the Order of the Phoenix", 600.00F));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(6L,
                "Harry Potter and the Half-Blood Prince", 599.00F));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(7L,
                "Harry Potter and the Deathly Hallows", 899.00F));
        ProductsRepositoryJdbcImpl productsRepositoryJdbcImpl =
                new ProductsRepositoryJdbcImpl(dataSource);
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS,
                productsRepositoryJdbcImpl.findAll());
    }

    @Test
    @DisplayName("Test for FindById method")
    public void testFindById() {
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(1L, "Harry Potter and the " +
                "Philosophers Stone", 799.00F));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(2L,
                "Harry Potter and the Chamber of Secrets", 600.00F));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(3L,
                "Harry Potter and the Prisoner of Azkaban", 899.00F));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(4L,
                "Harry Potter and the Goblet of Fire", 799.00F));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(5L,
                "Harry Potter and the Order of the Phoenix", 600.00F));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(6L,
                "Harry Potter and the Half-Blood Prince", 599.00F));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(7L,
                "Harry Potter and the Deathly Hallows", 899.00F));
        ProductsRepositoryJdbcImpl productsRepositoryJdbcImpl =
                new ProductsRepositoryJdbcImpl(dataSource);
        for (Product product : EXPECTED_FIND_ALL_PRODUCTS) {
            assertEquals(product.getId(),
                    productsRepositoryJdbcImpl.findById(product.getId()).get().getId());
        }

    }

}

package edu.school21.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.school21.models.Product;
import edu.school21.repositories.ProductsRepositoryJdbcImpl;
import org.junit.jupiter.api.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsRepositoryJdbcImplTest {
    private final List<Product> EXPECTED_FIND_ALL_PRODUCTS = new ArrayList<>();
    private final Product EXPECTED_SAVE_PRODUCT = new Product(8L,
            "The Hobbit", 499.00F);
    private final Product EXPECTED_UPDATED_PRODUCT =
            new Product(2L,
                    "Harry Potter and the Chamber of Secrets",
                    750.00F);
    private EmbeddedDatabase dataSource;

    @BeforeEach
    void init() throws SQLException {
        dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScripts("data.sql")
                .build();
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(1L,
                "Harry Potter and the Philosophers Stone",
                799.00F));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(2L,
                "Harry Potter and the Chamber of Secrets",
                600.00F));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(3L,
                "Harry Potter and the Prisoner of Azkaban",
                899.00F));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(4L,
                "Harry Potter and the Goblet of Fire",
                799.00F));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(5L,
                "Harry Potter and the Order of the Phoenix",
                600.00F));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(6L,
                "Harry Potter and the Half-Blood Prince",
                599.00F));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(7L,
                "Harry Potter and the Deathly Hallows",
                899.00F));
    }

    @Test
    @DisplayName("Test for FindAll method")
    public void testFindAll() {
        ProductsRepository productsRepositoryJdbcImpl =
                new ProductsRepositoryJdbcImpl(dataSource);
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS,
                productsRepositoryJdbcImpl.findAll());
    }

    @Test
    @DisplayName("Test for FindById method")
    public void testFindById() {
        ProductsRepository productsRepositoryJdbcImpl =
                new ProductsRepositoryJdbcImpl(dataSource);
        for (Product product : EXPECTED_FIND_ALL_PRODUCTS) {
            assertEquals(product,
                    productsRepositoryJdbcImpl.findById(product.getId()).get());
        }
        assertNull(productsRepositoryJdbcImpl.findById(9L).orElse(null));
    }

    @Test
    @DisplayName("Test for Update method")
    public void testUpdate() {
        ProductsRepository productsRepositoryJdbcImpl =
                new ProductsRepositoryJdbcImpl(dataSource);
        productsRepositoryJdbcImpl.update(EXPECTED_UPDATED_PRODUCT);
        assertEquals(EXPECTED_UPDATED_PRODUCT,
                productsRepositoryJdbcImpl.findById(2L).get());
    }

    @Test
    @DisplayName("Test for Save method")
    public void testSave() {
        ProductsRepository productsRepositoryJdbcImpl =
                new ProductsRepositoryJdbcImpl(dataSource);
        productsRepositoryJdbcImpl.save(EXPECTED_SAVE_PRODUCT);
        assertEquals(EXPECTED_SAVE_PRODUCT,
                productsRepositoryJdbcImpl.findById(8L).get());
    }

    @Test
    @DisplayName("Test for Delete method")
    public void testDelete() {
        ProductsRepository productsRepositoryJdbcImpl =
                new ProductsRepositoryJdbcImpl(dataSource);
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(8L,
                "The Hobbit", 499.00F));
        productsRepositoryJdbcImpl.delete(8L);
        assertNull(productsRepositoryJdbcImpl.findById(8L).orElse(null));
    }
}

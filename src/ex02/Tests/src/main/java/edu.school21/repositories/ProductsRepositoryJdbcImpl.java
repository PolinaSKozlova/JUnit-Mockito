package edu.school21.repositories;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    @Override
    List<Product> findAll();
    @Override
    Optional<Product> findById(Long id);
    @Override
    void update(Product product);
    @Override
    void save(Product product);
    @Override
    void delete(Long id);
}

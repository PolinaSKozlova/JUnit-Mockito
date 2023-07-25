package edu.school21.repositories;

public interface ProductsRepository {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    void update(Product product);

    void save(Product product);

    void delete(Long id);
}

package edu.school21.models;

import java.util.Objects;

public class Product {
    private long id;
    private String name;
    private float price;

    public Product(long bookId, String bookName, float bookPrice) {
        id = bookId;
        name = bookName;
        price = bookPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id
                && Float.compare(product.price, price) == 0
                && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
}

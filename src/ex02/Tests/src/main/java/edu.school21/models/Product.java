package models;

public class Product {
    private long id;
    private String name;
    private float price;

    public Product(long bookId, String bookName, float bookPrice) {
        id = bookId;
        name = bookName;
        price = bookPrice;
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

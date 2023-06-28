package model;

import java.util.List;

public class Order {
    private User user;
    private List<Product> products;

    private Order(User user, List<Product> prices) {
        this.user = user;
        this.products = prices;
    }

    public static Order createOrder(User user, List<Product> prices) {
        return new Order(user, prices);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}

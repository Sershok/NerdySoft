package sort;

import model.Order;
import model.Product;
import model.User;

import java.util.List;
import java.util.OptionalDouble;

public class SortByAverageAge {
    public double averageAge(List<Order> orders, Product product) {
        OptionalDouble averageAge = orders.stream()
                .filter(order -> order.getProducts().contains(product))
                .map(Order::getUser)
                .mapToDouble(User::getAge)
                .average();

        return averageAge.orElse(0.0);
    }
}

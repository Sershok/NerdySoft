import model.Order;
import model.Product;
import model.RealProduct;
import model.User;
import sort.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        //TODO Create model.User class with method createUser
        // model.User class fields: name, age;
        // Notice that we can only create user with createUser method without using constructor or builder
        User alice = User.createUser("Alice", 32);
        User bob = User.createUser("Bob", 19);
        User charlie = User.createUser("Charlie", 20);
        User john = User.createUser("John", 27);

        //TODO Create factory that can create a product for a specific type: Real or Virtual
        // model.Product class fields: name, price
        // model.Product Real class additional fields: size, weight
        // model.Product Virtual class additional fields: code, expiration date

        Product productA = ProductFactory.createRealProduct("model.Product A", 20.50, 10, 25);
        Product productB = ProductFactory.createRealProduct("model.Product B", 50, 6, 17);

        Product virtualProductC = ProductFactory.createVirtualProduct("model.Product C", 100, "xxx", LocalDate.of(2023, 5, 12));
        Product virtualProductD = ProductFactory.createVirtualProduct("model.Product D", 81.25, "yyy", LocalDate.of(2024, 6, 20));


        //TODO Create model.Order class with method createOrder
        // model.Order class fields: model.User, List<Price>
        // Notice that we can only create order with createOrder method without using constructor or builder
        List<Order> orders = new ArrayList<>() {{
            add(Order.createOrder(alice, List.of(productA, virtualProductC, virtualProductD)));
            add(Order.createOrder(bob, List.of(productA, productB)));
            add(Order.createOrder(charlie, List.of(productA, virtualProductD)));
            add(Order.createOrder(john, List.of(virtualProductC, virtualProductD, productA, productB)));
        }};

        //TODO 1). Create singleton class which will check the code is used already or not
        // Singleton class should have the possibility to mark code as used and check if code used
        // Example:
        // singletonClass.useCode("xxx")
        // boolean isCodeUsed = virtualProductCodeManager.isCodeUsed("xxx") --> true;
        // boolean isCodeUsed = virtualProductCodeManager.isCodeUsed("yyy") --> false;

        System.out.println("1. Create singleton class VirtualProductCodeManager \n");

        VirtualProductCodeManager singletonClass = VirtualProductCodeManager.getInstance();
        singletonClass.useCode("xxx");

        boolean isCodeUsed = singletonClass.isCodeUsed("xxx");
        System.out.println("Is code used: " + isCodeUsed + "\n");

        isCodeUsed = singletonClass.isCodeUsed("yyy");
        System.out.println("Is code used: " + isCodeUsed + "\n");

        //TODO 2). Create a functionality to get the most expensive ordered product
        Product mostExpensive = getMostExpensiveProduct(orders);
        System.out.println("2. Most expensive product: " + mostExpensive.getPrice() + "\n");

        //TODO 3). Create a functionality to get the most popular product(product bought by most users) among users
        Product mostPopular = getMostPopularProduct(orders);
        System.out.println("3. Most popular product: " + mostPopular.getName() + "\n");

        //TODO 4). Create a functionality to get average age of users who bought productB
        double averageAge = calculateAverageAge(productB, orders);
        System.out.println("4. Average age is: " + averageAge + "\n");

        //TODO 5). Create a functionality to return map with products as keys and a list of users
        // who ordered each product as values
        Map<Product, List<User>> productUserMap = getProductUserMap(orders);
        System.out.println("5. Map with products as keys and list of users as value \n");
        productUserMap.forEach((key, value) -> System.out.println("key: " + key + " " + "value: " + value + "\n"));

        //TODO 6). Create a functionality to sort/group entities:
        // a) Sort Products by price
        // b) Sort Orders by user age in descending order
        List<Product> productsByPrice = sortProductsByPrice(List.of(productA, productB, virtualProductC, virtualProductD));
        System.out.println("6. a) List of products sorted by price: " + productsByPrice.listIterator().next().getPrice() + "\n");
        List<Order> ordersByUserAgeDesc = sortOrdersByUserAgeDesc(orders);
        System.out.println("6. b) List of orders sorted by user agge in descending order: " + ordersByUserAgeDesc.listIterator().next().getUser().getAge() + "\n");

        //TODO 7). Calculate the total weight of each order
        Map<Order, Integer> result = calculateWeightOfEachOrder(orders);
        System.out.println("7. Calculate the total weight of each order \n");
        result.forEach((key, value) -> System.out.println("order: " + key + " " + "total weight: " + value + "\n"));
    }

    private static Product getMostExpensiveProduct(List<Order> orders) {
        orders.sort(new SortByPrice());
        return orders.listIterator().next().getProducts().listIterator().next();
    }

    private static Product getMostPopularProduct(List<Order> orders) {
        orders.sort(new SortByPopularProduct());
        return orders.listIterator().next().getProducts().listIterator().next();
    }

    private static double calculateAverageAge(Product product, List<Order> orders) {
        return new SortByAverageAge().averageAge(orders, product);
    }

    private static Map<Product, List<User>> getProductUserMap(List<Order> orders) {
        return orders.stream()
                .flatMap(order -> order.getProducts().stream()
                        .map(product -> new AbstractMap.SimpleEntry<>(product, order.getUser())))
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    private static List<Product> sortProductsByPrice(List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
    }

    private static List<Order> sortOrdersByUserAgeDesc(List<Order> orders) {
        orders.sort(new SortOrdersByUserAge());
        return orders;
    }

    private static Map<Order, Integer> calculateWeightOfEachOrder(List<Order> orders) {
        Map<Order, Integer> orderWeightMap = new HashMap<>();

        for (Order order : orders) {
            int totalWeight = 0;
            for (Product product : order.getProducts()) {
                totalWeight += product.getWeight();
            }
            orderWeightMap.put(order, totalWeight);
        }
        return orderWeightMap;
    }
}
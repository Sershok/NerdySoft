package sort;

import model.Order;

import java.util.Comparator;

public class SortByPopularProduct implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return o1.getProducts().listIterator().next().getName().compareTo(o2.getProducts().listIterator().next().getName());
    }
}

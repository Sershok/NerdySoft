package sort;

import model.Order;

import java.util.Comparator;

public class SortOrdersByUserAge implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return Double.compare(o1.getUser().getAge(), o2.getUser().getAge());
    }
}

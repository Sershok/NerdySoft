package sort;

import model.Order;

import java.util.Comparator;

public class SortByPrice implements Comparator<Order> {
    @Override
    public int compare(Order price1, Order price2) {
        return Double.compare(price2.getProducts().listIterator().next().getPrice(), price1.getProducts().listIterator().next().getPrice());
    }
}

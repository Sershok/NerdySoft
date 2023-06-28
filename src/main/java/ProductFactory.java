import model.RealProduct;
import model.VirtualProduct;

import java.time.LocalDate;

public class ProductFactory {

    public static RealProduct createRealProduct(String name, double price, double size, double weight) {
        return new RealProduct(price, name, size, weight);
    }

    public static VirtualProduct createVirtualProduct(String name, double price, String code, LocalDate expirationDate) {
        return new VirtualProduct(price, code, expirationDate, name);
    }
}

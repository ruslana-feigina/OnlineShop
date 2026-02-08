package inventory;

import exceptions.OutOfStockException;
import product.Product;
import exceptions.InvalidQuantityException;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<Product, Integer> stock = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        stock.put(product, quantity);
    }

    public boolean isAvailable(Product product, int needed) {
        if (product == null) return false;
        return stock.getOrDefault(product, 0) >= needed;
    }

    public void reduceStock(Product product, int sold)
            throws OutOfStockException, InvalidQuantityException {

        if (sold <= 0) throw new InvalidQuantityException(sold);

        int available = stock.getOrDefault(product, 0);
        if (sold > available) {
            throw new OutOfStockException(product.getName(), available, sold);
        }

        stock.put(product, available - sold);
    }

    public int getQuantity(Product product) { return stock.getOrDefault(product, 0); }
}
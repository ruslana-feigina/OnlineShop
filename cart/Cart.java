package cart;

import exceptions.InvalidQuantityException;
import inventory.Inventory;
import exceptions.CartFullException;
import exceptions.OutOfStockException;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();
    private static final int MAX_SIZE = 10;

    public List<CartItem> getItems() { return items; }

    public int getCount() { return items.size(); }

    public void addItem(CartItem item, Inventory stock) throws OutOfStockException, CartFullException, InvalidQuantityException {
        if (item == null) { throw new InvalidQuantityException("Cart item cannot be null."); }
        if (stock == null) { throw new InvalidQuantityException("Inventory cannot be null."); }

        if (!stock.isAvailable(item.getProduct(), item.getQuantity())) {
            throw new OutOfStockException(
                    item.getProduct().getName(),
                    stock.getQuantity(item.getProduct()),
                    item.getQuantity()
            );
        }

        if (items.size() >= MAX_SIZE) {
            throw new CartFullException(MAX_SIZE);
        }

        items.add(item);
        System.out.println( item.getProduct().getName() + " added to cart (quantity: " + item.getQuantity() + ")");
    }

    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }
}
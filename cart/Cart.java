package cart;

import exceptions.InvalidQuantityException;
import inventory.Inventory;
import exceptions.CartFullException;
import exceptions.OutOfStockException;

public class Cart {
    private final CartItem[] items = new CartItem[10];
    private int count = 0;

    public CartItem[] getItems() {
        CartItem[] currentItems = new CartItem[count];
        for (int i = 0; i < count; i++) {
            currentItems[i] = items[i];
        }
        return currentItems;
    }

    public int getCount() { return count; }

    public void addItem(CartItem item, Inventory stock) throws OutOfStockException, CartFullException, InvalidQuantityException {
        if (item == null) { throw new InvalidQuantityException("Cart item cannot be null."); }
        if (stock == null) { throw new InvalidQuantityException("Inventory cannot be null."); }

        if (!stock.isAvailable(item.getQuantity())) {
            throw new OutOfStockException(
                    item.getProduct().getName(),
                    stock.getQuantity(),
                    item.getQuantity()
            );
        }

        if (count >= items.length) { throw new CartFullException(items.length); }

        items[count++] = item;
        System.out.println( item.getProduct().getName() + " added to cart (quantity: " + item.getQuantity() + ")");
    }

    public double getTotalPrice() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += items[i].getTotalPrice();
        }
        return total;
    }
}
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

    public void addItem(CartItem item, Inventory stock) {
        if (!stock.isAvailable(item.getQuantity())) {
            System.out.println(item.getProduct().getName() + " is out of stock and cannot be added to cart.");
            return;
        }

        if (count < items.length) {
            items[count++] = item;
            System.out.println(item.getProduct().getName() + " added to cart (quantity: " + item.getQuantity() + ")");
        } else {
            System.out.println("Cart is full");
        }
    }

    public double getTotalPrice() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += items[i].getTotalPrice();
        }
        return total;
    }
}
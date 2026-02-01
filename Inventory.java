public class Inventory {
    private Product product;
    private int quantity;

    public Inventory(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }

    public void setProduct(Product product) { this.product = product; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public boolean isAvailable(int neededQuantity) {
        return neededQuantity <= quantity;
    }

    public void reduceStock(int sold) {
        if (sold <= quantity) {
            quantity -= sold;
            System.out.println(sold + " units of " + product.getName() + " sold. Remaining: " + quantity);
        } else {
            System.out.println("Not enough stock for " + product.getName());
        }
    }
}
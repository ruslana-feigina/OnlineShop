package exceptions;

public class CartFullException extends Exception {
    public CartFullException(int maxCapacity) {
        super("Cart is full. Max capacity: " + maxCapacity);
    }
}
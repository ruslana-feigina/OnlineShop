package exceptions;

public class OutOfStockException extends Exception {
    public OutOfStockException(String productName, int available, int requested) {
        super("Not enough stock for '" + productName +
                "'. Available: " + available +
                ", requested: " + requested);
    }
}
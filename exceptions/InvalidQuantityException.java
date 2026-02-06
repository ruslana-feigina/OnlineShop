package exceptions;

public class InvalidQuantityException extends Exception {
    public InvalidQuantityException(int quantity) {
        super("Quantity must be greater than 0. Provided: " + quantity);
    }

    public InvalidQuantityException(String message) {
        super(message);
    }
}
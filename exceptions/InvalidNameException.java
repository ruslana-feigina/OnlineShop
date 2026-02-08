package exceptions;

public class InvalidNameException extends Exception {
    public InvalidNameException() {
        super("Customer name cannot be blank.");
    }
}
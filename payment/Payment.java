package payment;

import interfaces.Notifiable;
import interfaces.Refundable;
import exceptions.UnsupportedMethodException;

public class Payment extends PaymentMethod implements Notifiable, Refundable {
    private String method;

    public Payment(double amount, String method) {
        super(amount);
        this.method = method;
    }

    public String getMethod() { return method; }

    public void setMethod(String method) throws UnsupportedMethodException {
        if (method.equalsIgnoreCase("Card") ||
                method.equalsIgnoreCase("Cash") ||
                method.equalsIgnoreCase("Online")) {
            this.method = method;
        } else {
            throw new UnsupportedMethodException(
                    "Wrong payment method '" + method + "'. Supported: Card, Cash, Online."
            );
        }
    }

    @Override
    public void processPayment() {
        System.out.println("Payment method: " + this.method);
        System.out.println("Payment successful: " + this.amount);
    }

    @Override
    public void notifyUser(String message) {
        System.out.println("Payment notification: " + message);
    }

    @Override
    public void refund() {
        System.out.println("Refund of " + amount + " has been processed for method " + method);
    }
}
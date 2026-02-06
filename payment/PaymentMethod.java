package payment;

public abstract class PaymentMethod {
    protected double amount;

    public PaymentMethod(double amount) {
        this.amount = amount;
    }

    public double getAmount() { return amount; }

    public void setAmount(double amount) { this.amount = amount; }

    public abstract void processPayment();
}
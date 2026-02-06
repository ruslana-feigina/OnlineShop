package order;

import cart.Cart;
import customer.Customer;
import interfaces.Cancellable;
import interfaces.Notifiable;
import interfaces.Trackable;

public class Order extends OrderEntity implements Cancellable, Notifiable, Trackable {
    private Customer customer;
    private Cart cart;
    private boolean valid;
    private String status;

    public Order(Customer customer, Cart cart) {
        this.customer = customer;
        this.cart = cart;
        this.valid = true;
        this.status = "Created";
    }

    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }

    public Cart getCart() { return cart; }

    public void setCart(Cart cart) { this.cart = cart; }

    @Override
    public double getTotalPrice() {
        if (!valid || cart == null) return 0;
        return cart.getTotalPrice();
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void updateStatus(String status) {
        this.status = status;
        System.out.println("Order status updated to: " + status);
    }


    @Override
    public void notifyUser(String message) {
        System.out.println("Notification to " + customer.getName() + ": " + message);
    }

    @Override
    public void cancel() {
        this.valid = false;
        this.status = "Cancelled";
        System.out.println("order.Order cancelled.");
    }
}
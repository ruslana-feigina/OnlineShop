import cart.Cart;
import cart.CartItem;
import customer.Customer;
import delivery.Delivery;
import discount.PercentageDiscount;
import exceptions.*;
import interfaces.*;
import inventory.Inventory;
import order.Order;
import payment.Payment;
import product.Product;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Customer customer = null;
        while (customer == null) {
            System.out.print("Enter customer name: ");
            String customerName = scanner.nextLine().trim();
            try {
                customer = new Customer(customerName);
            } catch (InvalidNameException e) {
                System.out.println("[Input] " + e.getMessage());
            }
        }
        System.out.println(customer.getName() + ", welcome to our Online Shop!");

        Product laptop = new Product("Laptop", 25000.0);
        Product phone = new Product("Smartphone", 15000.0);
        Product mouse = new Product("Mouse", 500.0);

        Inventory laptopStock = new Inventory(laptop, 5);
        Inventory phoneStock = new Inventory(phone, 0);
        Inventory mouseStock = new Inventory(mouse, 10);

        Cart cart = new Cart();
        try {
            cart.addItem(new CartItem(laptop, 1), laptopStock);
        } catch (InvalidQuantityException | OutOfStockException | CartFullException e) {
            System.out.println("[Cart] " + e.getMessage());
        }

        try {
            cart.addItem(new CartItem(phone, 1), phoneStock);
        } catch (InvalidQuantityException | OutOfStockException | CartFullException e) {
            System.out.println("[Cart] " + e.getMessage());
        }

        try {
            cart.addItem(new CartItem(mouse, 2), mouseStock);
        } catch (InvalidQuantityException | OutOfStockException | CartFullException e) {
            System.out.println("[Cart] " + e.getMessage());
        }

        Order order = new Order(customer, cart);
        Trackable trackableOrder = order;
        Notifiable notifiableOrder = order;

        trackableOrder.updateStatus("Created");
        notifiableOrder.notifyUser("Your order has been created.");

        System.out.println("Total before discount: " + order.getTotalPrice());

        PercentageDiscount discount = new PercentageDiscount();
        double totalAfterDiscount = discount.apply(order.getTotalPrice());

        Payment payment = new Payment(totalAfterDiscount, "Card");
        while (true) {
            System.out.print("Enter payment method (Card, Cash, Online) [default Card]: ");
            String method = scanner.nextLine().trim();
            if (method.isEmpty()) method = "Card";

            try {
                payment.setMethod(method);
                break;
            } catch (UnsupportedMethodException e) {
                System.out.println("[Payment] " + e.getMessage());
            }
        }

        payment.processPayment();
        trackableOrder.updateStatus("Paid");
        notifiableOrder.notifyUser("Payment successful!");
        payment.notifyUser("Thank you for your payment!");

        for (CartItem item : cart.getItems()) {
            Inventory stock = null;
            switch (item.getProduct().getName()) {
                case "Laptop":
                    stock = laptopStock;
                    break;
                case "Smartphone":
                    stock = phoneStock;
                    break;
                case "Mouse":
                    stock = mouseStock;
                    break;
                default:
                    stock = null;
            }

            if (stock != null) {
                try {
                    stock.reduceStock(item.getQuantity());
                } catch (InvalidQuantityException | OutOfStockException e) {
                    System.out.println("[Stock] " + e.getMessage());
                }
            }
        }

        Delivery delivery;
        Notifiable notifiableDelivery;

        while (true) {
            System.out.print("Enter delivery type (Self-pickup, NP Branch, NP Locker): ");
            String type = scanner.nextLine().trim();

            if (type.equalsIgnoreCase("Self-pickup") ||
                    type.equalsIgnoreCase("NP Branch") ||
                    type.equalsIgnoreCase("NP Locker")) {

                String city = "";
                String branchNumber = "";

                if (type.equalsIgnoreCase("NP Branch") || type.equalsIgnoreCase("NP Locker")) {
                    System.out.print("Enter city: ");
                    city = scanner.nextLine().trim();
                    System.out.print("Enter branch/locker number: ");
                    branchNumber = scanner.nextLine().trim();
                }

                delivery = new Delivery(type, city, branchNumber);
                notifiableDelivery = delivery;
                break;

            } else {
                System.out.println("Invalid delivery type. Try again.");
            }
        }

        trackableOrder.updateStatus("Shipped");
        notifiableDelivery.notifyUser("Your order is on the way.");

        trackableOrder.updateStatus("Delivered");
        notifiableOrder.notifyUser("Your order has been delivered.");

        Reviewable reviewableLaptop = laptop;
        System.out.print("Would you like to leave a review for your order? (yes/no): ");
        String leaveReview = scanner.nextLine();
        if (leaveReview.equalsIgnoreCase("yes")) {
            System.out.print("Enter your review comment: ");
            String comment = scanner.nextLine();
            reviewableLaptop.addReview(comment);
        }
        reviewableLaptop.printReviews();

        System.out.println("Order completed successfully!");
    }
}
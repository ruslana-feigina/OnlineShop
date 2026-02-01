import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine().trim();
        while (customerName.isEmpty()) {
            System.out.println("Name cannot be blank. Try again.");
            customerName = scanner.nextLine().trim();
        }
        Customer customer = new Customer(customerName);
        System.out.println(customer.getName() + ", welcome to our Online Shop!");

        Product laptop = new Product("Laptop", 25000.0);
        Product phone = new Product("Smartphone", 15000.0);
        Product mouse = new Product("Mouse", 500.0);

        Inventory laptopStock = new Inventory(laptop, 5);
        Inventory phoneStock = new Inventory(phone, 0);
        Inventory mouseStock = new Inventory(mouse, 10);

        Cart cart = new Cart();
        cart.addItem(new CartItem(laptop, 1), laptopStock);
        cart.addItem(new CartItem(phone, 1), phoneStock);
        cart.addItem(new CartItem(mouse, 2), mouseStock);

        Order order = new Order(customer, cart);
        Trackable trackableOrder = order;
        Cancellable cancellableOrder = order;
        Notifiable notifiableOrder = order;

        trackableOrder.updateStatus("Created");
        notifiableOrder.notifyUser("Your order has been created.");

        System.out.println("Total before discount: " + order.getTotalPrice());

        PercentageDiscount discount = new PercentageDiscount();
        double totalAfterDiscount = discount.apply(order.getTotalPrice());

        Payment payment = new Payment(totalAfterDiscount, "Card");
        Notifiable notifiablePayment = payment;
        Refundable refundablePayment = payment;

        System.out.print("Enter payment method (Card, Cash, Online) [default Card]: ");
        String method = scanner.nextLine().trim();
        if (method.isEmpty()) method = "Card";
        payment.setMethod(method);

        payment.processPayment();
        trackableOrder.updateStatus("Paid");
        notifiableOrder.notifyUser("Payment successful!");

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
                stock.reduceStock(item.getQuantity());
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
        System.out.print("Would you like to leave a review for the Laptop? (yes/no): ");
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
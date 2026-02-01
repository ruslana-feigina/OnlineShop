public class Delivery extends DeliveryMethod implements Notifiable {
    private String deliveryType;

    public Delivery(String deliveryType, String city, String branchNumber) {
        super(city, branchNumber);
        this.deliveryType = deliveryType;
    }

    public String getDeliveryType() { return deliveryType; }

    public void setDeliveryType(String deliveryType) { this.deliveryType = deliveryType; }

    @Override
    public void deliver(String customerName) {
        System.out.println("Delivery for " + customerName + " by " + deliveryType);
        switch (deliveryType) {
            case "Self-pickup":
                System.out.println("Customer will pick up the order from the store.");
                break;
            case "NP Branch":
                System.out.println("Customer will pick up from NP branch №" + branchNumber + " in " + city);
                break;
            case "NP Locker":
                System.out.println("Customer will pick up from NP locker №" + branchNumber + " in " + city);
                break;
            default:
                System.out.println("Unknown delivery type");
        }
        System.out.println("Your order is ready!");
    }

    @Override
    public void notifyUser(String message) {
        System.out.println("Delivery notification: " + message);
    }
}
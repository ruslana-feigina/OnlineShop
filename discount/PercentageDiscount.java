package discount;

public class PercentageDiscount extends Discount {
    private double percentage;
    public static final double DEFAULT_PERCENT = 10.0;

    public PercentageDiscount() {
        this.percentage = DEFAULT_PERCENT;
    }

    public PercentageDiscount(double percentage) {
        this.percentage = percentage;
    }

    public double getPercentage() { return percentage; }

    public void setPercentage(double percentage) { this.percentage = percentage; }

    @Override
    public double apply(double amount) {
        if (amount <= 0) return 0;
        double discounted = amount - (amount * percentage / 100);
        System.out.println("Applied " + percentage + "% discount. New total: " + discounted);
        return discounted;
    }
}
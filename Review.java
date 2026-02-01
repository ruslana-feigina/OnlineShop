public class Review {
    private Customer customer;
    private Product product;
    private String comment;

    public Review(Customer customer, Product product, String comment) {
        this.customer = customer;
        this.product = product;
        this.comment = comment;
    }

    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }

    public Product getProduct() { return product; }

    public void setProduct(Product product) { this.product = product; }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) { this.comment = comment; }

    public void printReview() {
        System.out.println(customer.getName() + " left comment to " + product.getName() + ": " + comment);
    }
}
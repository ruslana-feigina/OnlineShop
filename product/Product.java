package product;

import interfaces.Reviewable;

import java.util.HashSet;
import java.util.Set;

public class Product implements Reviewable {
    private final String name;
    private final Double price;

    private final Set<String> comments = new HashSet<>();

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }

    public Double getPrice() { return price; }

    @Override
    public void addReview(String comment) {
        comments.add(comment);
        System.out.println("Review added for " + name + ": \"" + comment + "\"");
    }

    @Override
    public void printReviews() {
        for (String comment : comments) {
            System.out.println("- " + comment);
        }
    }
}
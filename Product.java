import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Product implements Reviewable{
    private String name;
    private Double price;

    private final List<String> comments = new ArrayList<>();

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }


    @Override
    public void addReview(String comment) {
        comments.add(comment);
        System.out.println("Review added for " + name + ": \"" + comment + "\"");
    }

    @Override
    public void printReviews() {
        System.out.println("Reviews for " + name + ":");
        for (String comment : comments) {
            System.out.println("- " + comment);
        }
    }
}
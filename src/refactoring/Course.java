public class Course {
    private final String id;
    private final double price;

    public Course(String id, double price) {
        this.id = id;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }
}


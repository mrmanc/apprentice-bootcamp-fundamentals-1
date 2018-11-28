package checkout;

public class Product {
    private final int price;

    public Product(int price) {
        this.price = price;
    }

    public int price() {
        return price;
    }
}

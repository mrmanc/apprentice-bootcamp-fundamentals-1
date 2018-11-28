package checkout;

public class Product {
    private final String sku;
    private final int price;

    public Product(String sku, int price) {
        this.sku = sku;
        this.price = price;
    }

    public int price() {
        return price;
    }

    public String sku() {
        return sku;
    }
}

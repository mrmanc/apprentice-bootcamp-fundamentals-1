package checkout;

public class Discount {
    private final String sku;
    private final int discountAmount;
    private final int quantity;

    public Discount(String sku, int discountAmount, int quantity) {

        this.sku = sku;
        this.discountAmount = discountAmount;
        this.quantity = quantity;
    }

    int discount(Basket basket) {
        return discountAmount * (basket.numberOf(sku) / quantity);
    }
}

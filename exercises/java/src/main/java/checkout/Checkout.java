package checkout;

import java.util.List;

class Checkout {
    private int total;
    private Receipt receipt = new Receipt();
    private final Basket basket = new Basket();
    private final List<Discount> discounts;
    private final Catalogue catalogue;

    Checkout(List<Discount> discounts) {
        this.discounts = discounts;
        this.catalogue = new Catalogue(
                new Product("A", 50),
                new Product("B", 30),
                new Product("C", 20),
                new Product("D", 15));
    }

    void scan(String sku) {
        final Product product = catalogue.findProduct(sku);
        basket.add(product);
        total += product.price();

        addProductToReceipt(sku);
    }

    private void addProductToReceipt(String sku) {
        if ("A".equals(sku)) {
            receipt.scannedA();
        } else if ("B".equals(sku)) {
            receipt.scannedB();
        } else if ("C".equals(sku)) {
            receipt.scannedC();
        } else if ("D".equals(sku)) {
            receipt.scannedD();
        }
    }

    int total() {
        return total - discounts();
    }

    private int discounts() {
        int discountAmount = 0;
        for (Discount discount : discounts) {
            discountAmount += discount.discount(basket);
        }
        return discountAmount;
    }

    public String receipt() {
        return receipt.text();
    }
}

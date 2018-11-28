package checkout;

import java.util.Arrays;
import java.util.List;

class Checkout {
    private int total;
    private Receipt receipt = new Receipt();
    private final Basket basket = new Basket();
    private final List<Discount> discounts;

    Checkout() {
        final List<Discount> discounts = Arrays.asList(new Discount[]{
                new Discount("A", 20, 3),
                new Discount("B", 15, 2),
                new Discount("C", 10, 4),
                new Discount("D", 15, 5)
        });
        this.discounts = discounts;
    }

    void scan(String sku) {
        final Product product = findProduct(sku);
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

    private Product findProduct(String sku) {
        final Product product;
        if ("A".equals(sku)) {
            product = new Product("A", 50);
        } else if ("B".equals(sku)) {
            product = new Product("B", 30);
        } else if ("C".equals(sku)) {
            product = new Product("C", 20);
        } else if ("D".equals(sku)) {
            product = new Product("D", 15);
        } else {
            throw new IllegalArgumentException("Product sku not valid");
        }
        return product;
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

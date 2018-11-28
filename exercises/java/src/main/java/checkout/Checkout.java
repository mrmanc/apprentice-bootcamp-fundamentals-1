package checkout;

import java.util.HashSet;
import java.util.Set;

class Checkout {
    private int total;
    private Receipt receipt = new Receipt();
    private Set<Product> basket = new HashSet<>();
    private int discounts;

    void scan(String sku) {
        final Product product = findProduct(sku);
        basket.add(product);
        total += product.price();
        
        int discounts = 0;
        if ("A".equals(sku)) {
            if (numberOf("A") % 3 == 0) {
                discounts += 20;
            }
        } else if ("B".equals(sku)) {
            if (numberOf("B") % 2 == 0) {
                discounts += 15;
            }
        } else if ("C".equals(sku)) {
            if (numberOf("C") % 4 == 0) {
                discounts += 10;
            }
        } else if ("D".equals(sku)) {
            if (numberOf("D") % 5 == 0) {
                discounts += 15;
            }
        }
        this.discounts += discounts;
        addProductToReceipt(sku);
    }

    private int numberOf(String sku) {
        int result = 0;
        for (Product productInBasket : basket) {
            if (sku.equals(productInBasket.sku())) {
                result++;
            }
        }
        return result;
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
        }
        else {
            throw new IllegalArgumentException("Product sku not valid");
        }
        return product;
    }

    int total() {
        return total - discounts;
    }

    public String receipt() {
        return receipt.text();
    }
}

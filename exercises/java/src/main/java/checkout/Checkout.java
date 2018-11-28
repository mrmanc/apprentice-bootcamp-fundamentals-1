package checkout;

import java.util.HashSet;
import java.util.Set;

class Checkout {
    private int total;
    private Receipt receipt = new Receipt();
    private Set<Product> basket = new HashSet<>();

    void scan(String sku) {
        final Product product = findProduct(sku);
        basket.add(product);
        total += product.price();
        
        int numberOfA = 0;
        int numberOfB = 0;
        int numberOfC = 0;
        int numberOfD = 0;
        for (Product productInBasket : basket) {
            if ("A".equals(productInBasket.sku())) {
                numberOfA++;
            }
            if ("B".equals(productInBasket.sku())) {
                numberOfB++;
            }
            if ("C".equals(productInBasket.sku())) {
                numberOfC++;
            }
            if ("D".equals(productInBasket.sku())) {
                numberOfD++;
            }
        }
        
        int discounts = 0;
        if ("A".equals(sku)) {
            if (numberOfA % 3 == 0) {
                discounts += 20;
            }
        } else if ("B".equals(sku)) {
            if (numberOfB % 2 == 0) {
                discounts += 15;
            }
        } else if ("C".equals(sku)) {
            if (numberOfC % 4 == 0) {
                discounts += 10;
            }
        } else if ("D".equals(sku)) {
            if (numberOfD % 5 == 0) {
                discounts += 15;
            }
        }
        total -= discounts;
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
        }
        else {
            throw new IllegalArgumentException("Product sku not valid");
        }
        return product;
    }

    int total() {
        return total;
    }

    public String receipt() {
        return receipt.text();
    }
}

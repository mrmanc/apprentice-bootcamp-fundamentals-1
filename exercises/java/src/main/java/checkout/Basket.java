package checkout;

import java.util.HashSet;
import java.util.Set;

class Basket {
    private final Set<Product> basket = new HashSet<>();

    void add(Product product) {
        this.basket.add(product);
    }

    int numberOf(String sku) {
        int result = 0;
        for (Product productInBasket : this.basket) {
            if (sku.equals(productInBasket.sku())) {
                result++;
            }
        }
        return result;
    }
}

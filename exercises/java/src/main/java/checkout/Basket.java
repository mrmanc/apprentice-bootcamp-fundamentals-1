package checkout;

import java.util.ArrayList;
import java.util.List;

class Basket {
    private final List<Product> basket = new ArrayList<>();

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

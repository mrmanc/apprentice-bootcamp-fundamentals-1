package checkout;

import java.util.HashMap;
import java.util.Map;

public class Catalogue {
    private final Map<String, Product> products;

    public Catalogue(Product... products) {
        this.products = new HashMap<>();
        for (Product product : products) {
            this.products.put(product.sku(), product);
        }
    }

    Product findProduct(String sku) {
        return this.products.get(sku);
    }
}

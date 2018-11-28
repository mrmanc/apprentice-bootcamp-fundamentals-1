package checkout;

class Checkout {
    private int total;
    private Receipt receipt = new Receipt();
    private final Basket basket = new Basket();

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
        }
        else {
            throw new IllegalArgumentException("Product sku not valid");
        }
        return product;
    }

    int total() {
        return total - discounts();
    }

    private int discounts() {
        int discounts = 0;
        discounts += new Discount("A", 20, 3).discount(basket);
        discounts += new Discount("B", 15, 2).discount(basket);
        discounts += new Discount("C", 10, 4).discount(basket);
        discounts += new Discount("D", 15, 5).discount(basket);
        return discounts;
    }

    public String receipt() {
        return receipt.text();
    }
}

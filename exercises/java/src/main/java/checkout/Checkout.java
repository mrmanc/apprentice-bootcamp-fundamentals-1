package checkout;

class Checkout {
    private final Receipt receipt = new Receipt();
    private final Basket basket = new Basket();
    private final Discount[] discounts;
    private final Catalogue catalogue;

    Checkout(Discount... discounts) {
        this.discounts = discounts;
        this.catalogue = new Catalogue(
                new Product("A", 50),
                new Product("B", 30),
                new Product("C", 20),
                new Product("D", 15));
    }

    void scan(String sku) {
        basket.add(catalogue.findProduct(sku));
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
        return basket.price() - discounts();
    }

    private int discounts() {
        int discountAmount = 0;
        for (Discount discount : discounts) {
            discountAmount += discount.discount(basket);
        }
        return discountAmount;
    }

    String receipt() {
        return receipt.text();
    }
}

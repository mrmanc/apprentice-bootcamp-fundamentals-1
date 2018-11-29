package checkout;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class CheckoutTests {

    @Test
    public void nothingInBasketDoesNotApplyDiscounts() {
        Checkout checkout = new Checkout(new Discount("A", 20, 3));

        assertThat(checkout.total()).isEqualTo(0);
    }

    @Test
    public void oneA() {
        Checkout checkout = new Checkout(new Discount("A", 20, 3));

        checkout.scan("A");
        assertThat(checkout.total()).isEqualTo(50);
    }

    @Test
    public void twoA() {
        Checkout checkout = new Checkout(new Discount("A", 20, 3));

        checkout.scan("A");
        checkout.scan("A");
        assertThat(checkout.total()).isEqualTo(100);
    }

    @Test
    public void threeA() {
        Checkout checkout = new Checkout(new Discount("A", 20, 3));

        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        assertThat(checkout.total()).isEqualTo(130);
    }

    @Test
    public void fourA() {
        Checkout checkout = new Checkout(new Discount("A", 10, 4));

        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        assertThat(checkout.total()).isEqualTo(190);
    }


    @Test
    public void sixA() {
        Checkout checkout = new Checkout(new Discount("A", 20, 3));

        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");
        assertThat(checkout.total()).isEqualTo(260);
    }

    @Test
    public void oneB() {
        Checkout checkout = new Checkout(new Discount("B", 15, 2));

        checkout.scan("B");
        assertThat(checkout.total()).isEqualTo(30);
    }

    @Test
    public void twoB() {
        Checkout checkout = new Checkout(new Discount("B", 15, 2));

        checkout.scan("B");
        checkout.scan("B");
        assertThat(checkout.total()).isEqualTo(45);
    }

    @Test
    public void fourB() {
        Checkout checkout = new Checkout(new Discount("B", 15, 2));

        checkout.scan("B");
        checkout.scan("B");
        checkout.scan("B");
        checkout.scan("B");
        assertThat(checkout.total()).isEqualTo(90);
    }

    @Test
    public void fourC() {
        Checkout checkout = new Checkout(new Discount("C", 10, 4));

        checkout.scan("C");
        checkout.scan("C");
        checkout.scan("C");
        checkout.scan("C");
        assertThat(checkout.total()).isEqualTo(70);
    }

    @Test
    public void fiveD() {
        Checkout checkout = new Checkout(new Discount("D", 15, 5));

        checkout.scan("D");
        checkout.scan("D");
        checkout.scan("D");
        checkout.scan("D");
        checkout.scan("D");
        assertThat(checkout.total()).isEqualTo(60);
    }

    @Test
    public void simple() {
        Checkout checkout = new Checkout(
                new Discount("A", 20, 3),
                new Discount("B", 15, 2),
                new Discount("C", 10, 4),
                new Discount("D", 15, 5)
        );

        checkout.scan("A");
        assertThat(checkout.total()).isEqualTo(50);

        checkout.scan("B");
        assertThat(checkout.total()).isEqualTo(80);

        checkout.scan("C");
        assertThat(checkout.total()).isEqualTo(100);

        checkout.scan("D");
        assertThat(checkout.total()).isEqualTo(115);
    }

    @Test
    public void incremental() {
        Checkout checkout = new Checkout(
                new Discount("A", 20, 3),
                new Discount("B", 15, 2),
                new Discount("C", 10, 4),
                new Discount("D", 15, 5)
        );

        checkout.scan("A");
        assertThat(checkout.total()).isEqualTo(50);

        checkout.scan("B");
        assertThat(checkout.total()).isEqualTo(80);

        checkout.scan("A");
        assertThat(checkout.total()).isEqualTo(130);

        checkout.scan("A");
        assertThat(checkout.total()).isEqualTo(160);

        checkout.scan("B");
        assertThat(checkout.total()).isEqualTo(175);

        checkout.scan("C");
        assertThat(checkout.total()).isEqualTo(195);

        checkout.scan("B");
        assertThat(checkout.total()).isEqualTo(225);

        checkout.scan("C");
        assertThat(checkout.total()).isEqualTo(245);

        checkout.scan("D");
        assertThat(checkout.total()).isEqualTo(260);

        checkout.scan("D");
        assertThat(checkout.total()).isEqualTo(275);

        checkout.scan("D");
        assertThat(checkout.total()).isEqualTo(290);

        checkout.scan("C");
        assertThat(checkout.total()).isEqualTo(310);
    }
}

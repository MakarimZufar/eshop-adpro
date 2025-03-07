package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class PaymentTest {

    private Payment payment;
    private Order order;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product dummyProduct = new Product();
        dummyProduct.setProductId("prod-01");
        dummyProduct.setProductName("Dummy Product 01");
        dummyProduct.setProductQuantity(10);
        products.add(dummyProduct);
        
        order = new Order("order-001", products, 1708560000L, "Test Author");

        paymentData = new HashMap<>();
        paymentData.put("transactionId", "TXN001");

        payment = Payment.builder().build();
    }

    @Test
    void testPaymentBuilder() {
        // Given
        String id = "a2c6328-4a37-4664-83c7-f32db8620155";
        String method = "Cash";
        String status = "PENDING";

        // When
        payment = Payment.builder()
                .paymentId(id)
                .paymentMethod(method)
                .paymentStatus(status)
                .paymentData(paymentData)
                .linkOrder(order)
                .build();

        // Then
        assertEquals(id, payment.getPaymentId());
        assertEquals(method, payment.getPaymentMethod());
        assertEquals(status, payment.getPaymentStatus());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(order, payment.getLinkOrder());
    }

    @Test
    void testPaymentSetters() {
        // Given
        String id = "321e4567-e89b-12d3-a456-426614174000";
        String method = "PayPal";
        String status = "SUCCESS";
        Map<String, String> newPaymentData = new HashMap<>();
        newPaymentData.put("info", "Paid successfully");

        Product newProduct = new Product();
        newProduct.setProductId("prod-002");
        newProduct.setProductName("Dummy Product 2");
        newProduct.setProductQuantity(2);
        Order newOrder = new Order("order-002", Arrays.asList(newProduct), 1708560000L, "Another Author");

        // When
        payment.setPaymentId(id);
        payment.setPaymentMethod(method);
        payment.setPaymentStatus(status);
        payment.setPaymentData(newPaymentData);
        payment.setLinkOrder(newOrder);

        // Then
        assertEquals(id, payment.getPaymentId());
        assertEquals(method, payment.getPaymentMethod());
        assertEquals(status, payment.getPaymentStatus());
        assertEquals(newPaymentData, payment.getPaymentData());
        assertEquals(newOrder, payment.getLinkOrder());
    }
}


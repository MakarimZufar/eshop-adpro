package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {

    private PaymentRepository paymentRepository;
    private Payment payment1;
    private Payment payment2;
    private Order dummyOrder;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        List<Product> products = new ArrayList<>();
        Product dummyProduct = new Product();
        dummyProduct.setProductId("product-001");
        dummyProduct.setProductName("Dummy Product 01");
        dummyProduct.setProductQuantity(1);
        products.add(dummyProduct);

        dummyOrder = new Order("order-001", products, 1708560000L, "Test Author");

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("transactionId", "txn-001");

        payment1 = Payment.builder()
                .paymentId("payment-001")
                .paymentMethod("Cash")
                .paymentStatus("PENDING")
                .paymentData(paymentData1)
                .linkOrder(dummyOrder)
                .build();

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("transactionId", "txn-002");

        payment2 = Payment.builder()
                .paymentId("payment-002")
                .paymentMethod("PayPal")
                .paymentStatus("PENDING")
                .paymentData(paymentData2)
                .linkOrder(dummyOrder)
                .build();
    }

    @Test
    void testSaveNewPayment() {
        Payment saved = paymentRepository.save(payment1);
        assertNotNull(saved);
        assertEquals("payment-001", saved.getPaymentId());

        Payment found = paymentRepository.findById("payment-001");
        assertNotNull(found);
        assertEquals("Cash", found.getPaymentMethod());
    }

    @Test
    void testSaveUpdatePayment() {
        paymentRepository.save(payment1);
        Map<String, String> updatedData = new HashMap<>();
        updatedData.put("transactionId", "txn-001-updated");

        Payment updatedPayment = Payment.builder()
                .paymentId("payment-001")
                .paymentMethod("DebitCard")
                .paymentStatus("PENDING")
                .paymentData(updatedData)
                .linkOrder(dummyOrder)
                .build();

        Payment result = paymentRepository.save(updatedPayment);
        assertEquals("payment-001", result.getPaymentId());
        assertEquals("DebitCard", result.getPaymentMethod());

        Payment found = paymentRepository.findById("payment-001");
        assertEquals("DebitCard", found.getPaymentMethod());
        assertEquals(updatedData, found.getPaymentData());
    }

    @Test
    void testFindByIdNotFound() {
        Payment found = paymentRepository.findById("non-existent");
        assertNull(found);
    }

    @Test
    void testFindAllPayments() {
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);
        List<Payment> allPayments = paymentRepository.findAll();
        assertEquals(2, allPayments.size());
    }
}

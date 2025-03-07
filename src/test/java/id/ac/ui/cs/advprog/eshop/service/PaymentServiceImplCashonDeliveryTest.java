package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplCashonDeliveryTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Order dummyOrder;
    private List<Product> dummyProducts;

    @BeforeEach
    void setUp() {
        dummyProducts = new ArrayList<>();
        Product product = new Product();
        product.setProductId("prod-001");
        product.setProductName("Dummy Product");
        product.setProductQuantity(1);
        dummyProducts.add(product);

        dummyOrder = new Order("order-001", dummyProducts, 1708560000L, "Test Author");
    }

    @Test
    void testAddPaymentCashOnDeliveryWithValidData() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "123 Main St");
        paymentData.put("deliveryFee", "5000");

        when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Payment payment = paymentService.addPayment(dummyOrder, "Cash on Delivery", paymentData);

        assertEquals("PENDING", payment.getPaymentStatus());
        assertEquals("Cash on Delivery", payment.getPaymentMethod());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testAddPaymentCashOnDeliveryWithEmptyAddress() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", ""); // address kosong
        paymentData.put("deliveryFee", "5000");

        when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Payment payment = paymentService.addPayment(dummyOrder, "Cash on Delivery", paymentData);

        assertEquals("REJECTED", payment.getPaymentStatus());
    }

    @Test
    void testAddPaymentCashOnDeliveryWithNullDeliveryFee() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "456 Elm St");
        paymentData.put("deliveryFee", null); // deliveryFee null

        when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Payment payment = paymentService.addPayment(dummyOrder, "Cash on Delivery", paymentData);

        assertEquals("REJECTED", payment.getPaymentStatus());
    }

    @Test
    void testAddPaymentCashOnDeliveryWithMissingDeliveryFee() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "789 Oak St");

        when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Payment payment = paymentService.addPayment(dummyOrder, "Cash on Delivery", paymentData);

        assertEquals("REJECTED", payment.getPaymentStatus());
    }
}
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplBankTransferTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Order dummyOrder;

    @BeforeEach
    void setUp() {
        // Buat dummy order dengan setidaknya satu produk agar tidak error
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("prod-001");
        product.setProductName("Dummy Product");
        product.setProductQuantity(1);
        products.add(product);

        dummyOrder = new Order("order-001", products, 1708560000L, "Test Author");
    }

    @Test
    void testAddPaymentBankTransferWithValidData() {
        // Given: paymentData dengan bankName dan referenceCode valid
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank ABC");
        paymentData.put("referenceCode", "REF123456");

        when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Payment payment = paymentService.addPayment(dummyOrder, "Bank Transfer", paymentData);

        // Then: Karena data lengkap, status tetap default "PENDING"
        assertEquals("PENDING", payment.getPaymentStatus());
        assertEquals("Bank Transfer", payment.getPaymentMethod());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testAddPaymentBankTransferWithEmptyBankName() {
        // Given: bankName kosong, referenceCode valid
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "REF123456");

        when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Payment payment = paymentService.addPayment(dummyOrder, "Bank Transfer", paymentData);

        // Then: Karena bankName kosong, status harus REJECTED
        assertEquals("REJECTED", payment.getPaymentStatus());
    }

    @Test
    void testAddPaymentBankTransferWithNullReferenceCode() {
        // Given: bankName valid, referenceCode null
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank XYZ");
        paymentData.put("referenceCode", null);

        when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Payment payment = paymentService.addPayment(dummyOrder, "Bank Transfer", paymentData);

        // Then: Karena referenceCode null, status harus REJECTED
        assertEquals("REJECTED", payment.getPaymentStatus());
    }

    @Test
    void testAddPaymentBankTransferWithMissingReferenceCode() {
        // Given: bankName valid, tapi referenceCode tidak ada
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank 123");

        when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Payment payment = paymentService.addPayment(dummyOrder, "Bank Transfer", paymentData);

        // Then: Karena referenceCode tidak ada, status harus REJECTED
        assertEquals("REJECTED", payment.getPaymentStatus());
    }
}
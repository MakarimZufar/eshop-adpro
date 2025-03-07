package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {

    private List<Payment> payments = new ArrayList<>();

    public Payment save(Payment payment) {
        // Jika payment dengan ID yang sama sudah ada, update; jika tidak, tambahkan baru.
        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getPaymentId().equals(payment.getPaymentId())) {
                payments.set(i, payment);
                return payment;
            }
        }
        payments.add(payment);
        return payment;
    }

    public Payment findById(String paymentId) {
        for (Payment payment : payments) {
            if (payment.getPaymentId().equals(paymentId)) {
                return payment;
            }
        }
        return null;
    }

    public List<Payment> findAll() {
        return new ArrayList<>(payments);
    }
}


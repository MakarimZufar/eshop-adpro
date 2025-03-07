package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String status = "PENDING"; 

        if ("Cash on Delivery".equals(method)) {
            String voucherCode = paymentData.get("voucherCode");
            if (voucherCode != null) {
                if (isValidVoucherCode(voucherCode)) {
                    status = "SUCCESS";
                } else {
                    status = "REJECTED";
                }
            }
            else {
                boolean hasAddress = paymentData.containsKey("address");
                boolean hasFee = paymentData.containsKey("deliveryFee");

                if (hasAddress || hasFee) {
                    if (!hasAddress || !hasFee) {
                        status = "REJECTED";
                    } else {
                        String address = paymentData.get("address");
                        String fee = paymentData.get("deliveryFee");
                        if (address == null || address.trim().isEmpty() ||
                                fee == null || fee.trim().isEmpty()) {
                            status = "REJECTED";
                        }
                    }
                }
            }
        }

        Payment payment = Payment.builder()
                .paymentId(UUID.randomUUID().toString())
                .paymentMethod(method)
                .paymentData(paymentData)
                .paymentStatus(status)
                .linkOrder(order)
                .build();

        return paymentRepository.save(payment);
    }


    @Override
    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);
        if ("SUCCESS".equals(status)) {
            payment.getLinkOrder().setStatus("SUCCESS");
        } else if ("REJECTED".equals(status)) {
            payment.getLinkOrder().setStatus("FAILED");
        }
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    private boolean isValidVoucherCode(String voucherCode) {
        if (voucherCode.length() != 16 && !voucherCode.startsWith("ESHOP")) {
            return false;
        }
        // Ambil semua digit dari voucher code
        String digits = voucherCode.replaceAll("[^0-9]", "");
        return digits.length() == 8;
    }
}

package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Payment {
    private String paymentId;
    private String paymentMethod;
    private String paymentStatus;
    private Map<String, String> paymentData;
    private Order linkOrder;

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.paymentStatus = status;
        } else {
            throw new IllegalArgumentException("Invalid Payment Status: " + status);
        }
    }
}
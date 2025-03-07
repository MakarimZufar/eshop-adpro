package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;
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
}
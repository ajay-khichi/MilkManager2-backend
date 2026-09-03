package in.ignishers.milkymistbackend.models;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PaymentId implements Serializable {

    private String sellerId;
    private Long paymentId;
}

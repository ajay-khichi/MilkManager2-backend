package in.ignishers.milkymistbackend.models;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MilkPriceId implements Serializable {

    private String sellerId;
    private Long priceId;
}

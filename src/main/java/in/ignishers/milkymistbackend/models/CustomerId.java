package in.ignishers.milkymistbackend.models;

import lombok.Getter;
import lombok.Setter;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CustomerId implements Serializable {

    private String sellerId;
    private Long customerId;
}

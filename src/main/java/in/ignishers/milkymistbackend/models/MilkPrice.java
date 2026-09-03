package in.ignishers.milkymistbackend.models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "milk_prices")
@IdClass(MilkPriceId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MilkPrice {

    @Id
    @Column(name = "seller_id")
    private String sellerId;

    @Id
    @Column(name = "price_id")
    private Long priceId;

    @Column(name = "price_per_litre")
    private BigDecimal pricePerLitre;

    // Stored as text in the schema; kept as String to match exactly.
    @Column(name = "effective_date")
    private String effectiveDate;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", referencedColumnName = "seller_id",
            insertable = false, updatable = false)
    private Seller seller;
}

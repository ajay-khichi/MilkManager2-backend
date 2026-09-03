package in.ignishers.milkymistbackend.models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "payments")
@IdClass(PaymentId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @Column(name = "seller_id")
    private String sellerId;

    @Id
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "customer_id_fk")
    private Long customerIdFk;

    // Stored as text in the schema; kept as String to match exactly.
    @Column(name = "payment_date")
    private String paymentDate;

    @Column(name = "payment_amount")
    private BigDecimal paymentAmount;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", referencedColumnName = "seller_id",
            insertable = false, updatable = false)
    private Seller seller;

    // (seller_id, customer_id_fk) -> customers(seller_id, customer_id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "seller_id", referencedColumnName = "seller_id",
                    insertable = false, updatable = false),
            @JoinColumn(name = "customer_id_fk", referencedColumnName = "customer_id",
                    insertable = false, updatable = false)
    })
    private Customer customer;
}

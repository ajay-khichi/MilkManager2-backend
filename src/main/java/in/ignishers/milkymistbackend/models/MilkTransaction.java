package in.ignishers.milkymistbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Table(name = "milk_transactions")
@Entity
@IdClass(MilkTransactionId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MilkTransaction {

    @Id
    @Column(name = "seller_id")
    private String sellerId;

    @Id
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "customer_id_fk")
    private Long customerIdFk;

    @Column(name = "transaction_date")
    private String transactionDate;

    @Column(name = "transaction_session")
    private String transactionSession;

    @Column(name = "transaction_quantity")
    private String transactionQuantity;

    @Column(name = "transaction_amount")
    private String transactionAmount;

    @Column(name = "transaction_timestamp")
    private String transactionTimestamp;

    @Column(name = "transaction_payment_mode")
    private String transactionPaymentMode;

    @Column(name = "transaction_milk_type")
    private String transactionMilkType;

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

package in.ignishers.milkymistbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name="customers")
@IdClass(CustomerId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @Column(name = "seller_id")
    private String sellerId;

    @Id
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "route_id_fk")
    private Long routeIdFk;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_mobile")
    private String customerMobile;

    @Column(name = "address_detail")
    private String addressDetail;

    @Column(name = "default_qty_morning")
    private BigDecimal defaultQtyMorning;

    @Column(name = "default_qty_evening")
    private BigDecimal defaultQtyEvening;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "auto_entry_enabled")
    private Integer autoEntryEnabled;

    @Column(name = "customer_due_balance")
    private BigDecimal customerDueBalance;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

//    seller_id (PK) also FK to sellers
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "seller_id",
            referencedColumnName = "seller_id",
            insertable = false, updatable = false
    )
    private Seller seller;

    // (seller_id, route_id_fk) -> route_groups(seller_id, group_id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(
                    name = "seller_id",
                    referencedColumnName = "seller_id",
                    insertable = false, updatable = false
            ),
            @JoinColumn(
                    name = "route_id_fk",
                    referencedColumnName = "group_id",
                    insertable = false, updatable = false
            )
    })
    private RouteGroup routeGroup;

}

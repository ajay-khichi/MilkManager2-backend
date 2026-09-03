package in.ignishers.milkymistbackend.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "route_groups")
@IdClass(RouteGroupId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteGroup {
    @Id
    @Column(name = "seller_id")
    private String sellerId;

    @Id
    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "parent_group_id")
    private Long parentGroupId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    // seller_id is part of the PK and also a FK to sellers.seller_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", referencedColumnName = "seller_id",
            insertable = false, updatable = false)
    private Seller seller;


}

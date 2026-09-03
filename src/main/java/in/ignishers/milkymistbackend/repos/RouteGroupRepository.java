package in.ignishers.milkymistbackend.repos;

import in.ignishers.milkymistbackend.models.RouteGroup;
import in.ignishers.milkymistbackend.models.RouteGroupId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteGroupRepository extends JpaRepository<RouteGroup, RouteGroupId> {

    // All route groups for a given seller
    List<RouteGroup> findBySellerId(String sellerId);

    // All route groups for a seller, ordered for display
    List<RouteGroup> findBySellerIdOrderBySortOrderAsc(String sellerId);

    // Sub-groups under a given parent (for hierarchy/tree screens)
    List<RouteGroup> findBySellerIdAndParentGroupId(String sellerId, Long parentGroupId);
}

package in.ignishers.milkymistbackend.repos;

import in.ignishers.milkymistbackend.models.Customer;
import in.ignishers.milkymistbackend.models.CustomerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, CustomerId> {

    // All customers for a seller
    List<Customer> findBySellerId(String sellerId);

    // All customers on a given route, for a seller
    List<Customer> findBySellerIdAndRouteIdFk(String sellerId, Long routeIdFk);

    // Display order (for route-wise listing screens)
    List<Customer> findBySellerIdAndRouteIdFkOrderBySortOrderAsc(String sellerId, Long routeIdFk);

    // Lookup by mobile number, scoped to seller
    Optional<Customer> findBySellerIdAndCustomerMobile(String sellerId, String customerMobile);

    // Customers with auto-entry enabled, for scheduled/cron entry jobs
    List<Customer> findBySellerIdAndAutoEntryEnabled(String sellerId, Integer autoEntryEnabled);

    // Simple name search within a seller's customer list
    List<Customer> findBySellerIdAndCustomerNameContainingIgnoreCase(String sellerId, String namePart);
}


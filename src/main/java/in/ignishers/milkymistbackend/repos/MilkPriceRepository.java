package in.ignishers.milkymistbackend.repos;

import in.ignishers.milkymistbackend.models.MilkPrice;
import in.ignishers.milkymistbackend.models.MilkPriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MilkPriceRepository extends JpaRepository<MilkPrice, MilkPriceId> {

    // Full price history for a seller
    List<MilkPrice> findBySellerId(String sellerId);

    // Price effective on/around a given date (effective_date is stored as text in the schema)
    List<MilkPrice> findBySellerIdAndEffectiveDate(String sellerId, String effectiveDate);

    // Most recently updated price row for a seller — handy as a "current price" lookup
    Optional<MilkPrice> findFirstBySellerIdOrderByUpdatedAtDesc(String sellerId);
}

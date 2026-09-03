package in.ignishers.milkymistbackend.repos;

import in.ignishers.milkymistbackend.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, String> {

    /// PK type = String (seller_id) — no @IdClass needed, single column PK
}

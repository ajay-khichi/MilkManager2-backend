package in.ignishers.milkymistbackend.repos;

import in.ignishers.milkymistbackend.models.Payment;
import in.ignishers.milkymistbackend.models.PaymentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, PaymentId> {

    // All payments for a seller
    List<Payment> findBySellerId(String sellerId);

    // Payment history for one customer, under a seller
    List<Payment> findBySellerIdAndCustomerIdFk(String sellerId, Long customerIdFk);

    // Payment history for a customer, oldest first (for a running ledger)
    List<Payment> findBySellerIdAndCustomerIdFkOrderByPaymentDateAsc(String sellerId, Long customerIdFk);

    // Payments received on a given date, for a seller (date stored as text)
    List<Payment> findBySellerIdAndPaymentDate(String sellerId, String paymentDate);
}

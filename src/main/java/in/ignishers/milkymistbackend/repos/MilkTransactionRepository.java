package in.ignishers.milkymistbackend.repos;

import in.ignishers.milkymistbackend.models.MilkTransaction;
import in.ignishers.milkymistbackend.models.MilkTransactionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MilkTransactionRepository extends JpaRepository<MilkTransaction, MilkTransactionId> {

    // All transactions for a seller
    List<MilkTransaction> findBySellerId(String sellerId);

    // All transactions for one customer, under a seller
    List<MilkTransaction> findBySellerIdAndCustomerIdFk(String sellerId, Long customerIdFk);

    // All transactions on a given date (date stored as text)
    List<MilkTransaction> findBySellerIdAndTransactionDate(String sellerId, String transactionDate);

    // Transactions for a specific date + session (e.g. daily route-wise entry sheet: morning/evening)
    List<MilkTransaction> findBySellerIdAndTransactionDateAndTransactionSession(
            String sellerId, String transactionDate, String transactionSession);

    // Ledger for one customer over time
    List<MilkTransaction> findBySellerIdAndCustomerIdFkOrderByTransactionDateAsc(
            String sellerId, Long customerIdFk);
}


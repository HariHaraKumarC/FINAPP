package hari.app.finapp.repositories;

import hari.app.finapp.models.FDTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by HariHaraKumar on 18/12/2017.
 */

public interface FDTransactionsRepository extends JpaRepository<FDTransactions,Long> {
    @Transactional
    List<FDTransactions> findAllByFixedDepositsFixedDepositsId(Long fixedDepositsId);
}

package hari.app.finapp.repositories;

import hari.app.finapp.models.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by HariHaraKumar on 8/12/2017.
 */

@Repository
public interface SavingsRepository extends JpaRepository<Savings,Long> {

}

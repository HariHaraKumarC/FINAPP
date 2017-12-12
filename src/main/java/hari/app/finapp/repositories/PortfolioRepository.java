package hari.app.finapp.repositories;

import hari.app.finapp.models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by HariHaraKumar on 12/2/2017.
 */

public interface PortfolioRepository extends JpaRepository<Portfolio,Long> {

    @Transactional
    List<Portfolio> findAllByUserUserId(Long userId);

}

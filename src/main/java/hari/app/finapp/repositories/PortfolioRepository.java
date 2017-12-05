package hari.app.finapp.repositories;

import hari.app.finapp.models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by HariHaraKumar on 12/2/2017.
 */
public interface PortfolioRepository extends JpaRepository<Portfolio,Long> {

}

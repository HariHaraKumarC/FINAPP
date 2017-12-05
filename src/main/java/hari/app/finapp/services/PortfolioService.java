package hari.app.finapp.services;

import hari.app.finapp.models.Portfolio;
import java.util.List;

/**
 * Created by HariHaraKumar on 12/3/2017.
 */
public interface PortfolioService {

    void savePortfolio(Portfolio portfolio);

    void updatePortfolioName(Long portfolioId,String portfolioName);

    void updatePortfolioInvestmentAmount(Long portfolioId,double amount,int opCode);

    void updatePortfolioGainLossAmount(Long portfolioId,double amount,int opCode);

    void deleteAllPortfolio();

    void deletePortfolioById(Long portfolioId);

    Portfolio findById(Long portfolioId);

    boolean isPortfolioExists(Portfolio portfolio);

}

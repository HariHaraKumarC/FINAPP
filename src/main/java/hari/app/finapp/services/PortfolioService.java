package hari.app.finapp.services;

import hari.app.finapp.models.Portfolio;
import java.util.List;

/**
 * Created by HariHaraKumar on 12/3/2017.
 */
public interface PortfolioService {

    void savePortfolio(Portfolio portfolio);

    void updatePortfolioName(long portfolioId,String portfolioName);

    void updatePortfolioInvestmentAmount(long portfolioId,double amount,int opCode);

    void updatePortfolioGainLossAmount(long portfolioId,double amount,int opCode);

    void deleteAllPortfolio();

    void deletePortfolioById(long portfolioId);

    Portfolio findById(long portfolioId);

    boolean isPortfolioExists(Portfolio portfolio);

}

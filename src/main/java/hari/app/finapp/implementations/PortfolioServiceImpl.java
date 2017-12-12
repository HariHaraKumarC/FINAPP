package hari.app.finapp.implementations;

import hari.app.finapp.models.Portfolio;
import hari.app.finapp.repositories.PortfolioRepository;
import hari.app.finapp.services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by HariHaraKumar on 12/3/2017.
 */
@Service("portfolioService")
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    PortfolioRepository portfolioRepo;

    @Override
    public void savePortfolio(Portfolio portfolio){
        portfolioRepo.save(portfolio);
    }

    @Override
    public void updatePortfolioName(long portfolioId,String portfolioName){
        Portfolio pf=findById(portfolioId);
        pf.setName(portfolioName);
        savePortfolio(pf);
    }

    @Override
    public void deleteAllPortfolio() {
        portfolioRepo.deleteAll();
    }

    @Override
    public void deletePortfolioById(long portfolioId) {
        portfolioRepo.delete(portfolioId);
    }

    @Override
    public Portfolio findById(long portfolioId){
       return portfolioRepo.findOne(portfolioId);
    }

    @Override
     public boolean isPortfolioExists(Portfolio portfolio) {
         return findById(portfolio.getPortfolioId())!=null;
    }

    @Override
    public List<Portfolio> listAllPortfolios(Long userId) {
        return portfolioRepo.findAllByUserUserId(userId);
    }
}

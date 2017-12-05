package hari.app.finapp.implementations;

import hari.app.finapp.models.Portfolio;
import hari.app.finapp.repositories.PortfolioRepository;
import hari.app.finapp.services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by HariHaraKumar on 12/3/2017.
 */
@Service("portfolioService")
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    PortfolioRepository portfolioRepo;

    public void savePortfolio(Portfolio portfolio){
        portfolioRepo.save(portfolio);
    }

    public void updatePortfolioName(Long portfolioId,String portfolioName){
        Portfolio pf=findById(portfolioId);
        pf.setName(portfolioName);
        savePortfolio(pf);
    }

    public void updatePortfolioInvestmentAmount(Long portfolioId, double amount,int opCode) {
        Portfolio pf=findById(portfolioId);
        BigDecimal pfInvAmount=BigDecimal.valueOf(pf.getInvestmentAmount());
        BigDecimal changeAmount=BigDecimal.valueOf(amount);
        BigDecimal totalInvAmount=BigDecimal.valueOf(0.00);
        if(opCode==1){
            totalInvAmount=pfInvAmount.add(changeAmount);
        }else if(opCode==2){
            totalInvAmount=pfInvAmount.subtract(changeAmount);
        }
        totalInvAmount=totalInvAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
        pf.setInvestmentAmount(totalInvAmount.doubleValue());
        savePortfolio(pf);
    }

    public void updatePortfolioGainLossAmount(Long portfolioId, double amount,int opCode) {
        Portfolio pf=findById(portfolioId);
        BigDecimal pfGainLossAmount=BigDecimal.valueOf(pf.getRealizedGainLossAmount());
        BigDecimal changeAmount=BigDecimal.valueOf(amount);
        BigDecimal totalGainLossAmount=BigDecimal.valueOf(0.00);
        if(opCode==1){
            totalGainLossAmount=pfGainLossAmount.add(changeAmount);
        }else if(opCode==2){
            totalGainLossAmount=pfGainLossAmount.subtract(changeAmount);
        }
        totalGainLossAmount=totalGainLossAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
        pf.setRealizedGainLossAmount(totalGainLossAmount.doubleValue());
        savePortfolio(pf);
    }

    public void deleteAllPortfolio() {
        portfolioRepo.deleteAll();
    }

    public void deletePortfolioById(Long portfolioId) {
        portfolioRepo.delete(portfolioId);
    }

    public Portfolio findById(Long portfolioId){
       return portfolioRepo.findOne(portfolioId);
    }

     public boolean isPortfolioExists(Portfolio portfolio) {
         return findById(portfolio.getPortfolioId())!=null;
    }
}

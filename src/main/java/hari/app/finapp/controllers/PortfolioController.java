package hari.app.finapp.controllers;

import hari.app.finapp.models.Portfolio;
import hari.app.finapp.repositories.PortfolioRepository;
import hari.app.finapp.services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by HariHaraKumar on 12/2/2017.
 */
@RestController
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    /**
     * Create new portfolio
     * @param portfolio
     * @return String
     */
    @RequestMapping(value = "/portfolio/create",method= RequestMethod.POST)
    public String createPortfolio(@RequestBody Portfolio portfolio){
        portfolioService.savePortfolio(portfolio);
        return "Portfolio successfully created";
    }

    /**
     * Update portfolio Name
     * @param portfolioId,portfolioName
     * @return String
     * @throws Exception
     */
    @RequestMapping(value = "/portfolio/updateName",method= RequestMethod.PUT)
    public String updatePortfolioName(@RequestParam("portfolioId") Long portfolioId,@RequestParam("portfolioName") String portfolioName) throws Exception{
        if(portfolioService.findById(portfolioId)!=null){
            portfolioService.updatePortfolioName(portfolioId,portfolioName);
        }else{
            throw new Exception("Portfolio "+portfolioId+" doesn't exists");
        }
        return "Portfolio "+portfolioId+" successfully updated";
    }

    /**
     * Delete portfolio
     * @param portfolioId
     * @return String
     */
    @RequestMapping(value = "/portfolio/delete",method= RequestMethod.DELETE)
    public String deletePortfolio(@RequestParam("portfolioId") Long portfolioId)throws Exception{
        if(portfolioService.findById(portfolioId)!=null){
            portfolioService.deletePortfolioById(portfolioId);
        }else{
            throw new Exception("Portfolio "+portfolioId+" doesn't exists");
        }
        return "Portfolio "+portfolioId+" successfully deleted";
    }

    /**
     * Get Specific portfolio
     * @param portfolioId
     * @return Portfolio
     */
    @RequestMapping(value = "/portfolio/details",method= RequestMethod.GET)
    public Portfolio getPortfolio(@RequestParam("portfolioId") Long portfolioId)throws Exception{
        Portfolio pf=portfolioService.findById(portfolioId);
        if(pf==null){
            throw new Exception("Portfolio "+portfolioId+" doesn't exists");
        }
        return pf;
    }
}

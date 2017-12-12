package hari.app.finapp.controllers;

import hari.app.finapp.models.Portfolio;
import hari.app.finapp.services.PortfolioService;
import hari.app.finapp.utils.FinappCustomResponseMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     * @return ResponseEntity
     */
    @RequestMapping(value = "/portfolio/create",method= RequestMethod.POST)
    public ResponseEntity<FinappCustomResponseMessageHandler> createPortfolio(@RequestBody Portfolio portfolio){
        portfolioService.savePortfolio(portfolio);
        return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("Portfolio successfully created"), HttpStatus.OK);
    }

    /**
     * Update portfolio Name
     * @param portfolioId,portfolioName
     * @return ResponseEntity
     */
    @RequestMapping(value = "/portfolio/updateName",method= RequestMethod.PUT)
    public ResponseEntity<FinappCustomResponseMessageHandler> updatePortfolioName(@RequestParam("portfolioId") long portfolioId, @RequestParam("portfolioName") String portfolioName){
        if(portfolioService.findById(portfolioId)!=null){
            portfolioService.updatePortfolioName(portfolioId,portfolioName);
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("Portfolio "+portfolioId+" successfully updated"), HttpStatus.OK);
        }else{
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("Portfolio "+portfolioId+" doesn't exists"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete portfolio
     * @param portfolioId
     * @return ResponseEntity
     */
    @RequestMapping(value = "/portfolio/delete",method= RequestMethod.DELETE)
    public ResponseEntity<FinappCustomResponseMessageHandler> deletePortfolio(@RequestParam("portfolioId") long portfolioId){
        if(portfolioService.findById(portfolioId)!=null){
            portfolioService.deletePortfolioById(portfolioId);
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("Portfolio "+portfolioId+" successfully deleted"), HttpStatus.OK);
        }else{
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("Portfolio "+portfolioId+" doesn't exists"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get Specific portfolio
     * @param portfolioId
     * @return ResponseEntity
     */
    @RequestMapping(value = "/portfolio/details",method= RequestMethod.GET)
    public ResponseEntity<?> getPortfolio(@RequestParam("portfolioId") long portfolioId){
        Portfolio pf=portfolioService.findById(portfolioId);
        if(pf==null){
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("Portfolio "+portfolioId+" doesn't exists"), HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<Portfolio>(pf,HttpStatus.OK);
        }
    }

    /**
     * Fetch all portfolios for the user
     * @param userId
     * @return ResponseEntity
     */
    @RequestMapping(value = "/portfolio/listAll",method= RequestMethod.GET)
    public ResponseEntity<List<Portfolio>> listAllPortfolios(@RequestParam("userId") long userId){
        List<Portfolio> portfolios=portfolioService.listAllPortfolios(userId);
        return new ResponseEntity<List<Portfolio>>(portfolios, HttpStatus.OK);
    }
}

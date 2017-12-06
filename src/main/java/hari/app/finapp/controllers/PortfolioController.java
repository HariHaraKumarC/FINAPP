package hari.app.finapp.controllers;

import hari.app.finapp.models.Portfolio;
import hari.app.finapp.services.PortfolioService;
import hari.app.finapp.utils.FinappResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/portfolio/create",method= RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FinappResponseHandler> createPortfolio(@RequestBody Portfolio portfolio){
        portfolioService.savePortfolio(portfolio);
        return new ResponseEntity<FinappResponseHandler>(new FinappResponseHandler(HttpStatus.OK.value(),HttpStatus.OK.getReasonPhrase(),"Portfolio successfully created"), HttpStatus.OK);
    }

    /**
     * Update portfolio Name
     * @param portfolioId,portfolioName
     * @return String
     * @throws Exception
     */
    @RequestMapping(value = "/portfolio/updateName",method= RequestMethod.PUT)
    public String updatePortfolioName(@RequestParam("portfolioId") long portfolioId,@RequestParam("portfolioName") String portfolioName) throws Exception{
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
    public String deletePortfolio(@RequestParam("portfolioId") long portfolioId)throws Exception{
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
    public Portfolio getPortfolio(@RequestParam("portfolioId") long portfolioId)throws Exception{
        Portfolio pf=portfolioService.findById(portfolioId);
        if(pf==null){
            throw new Exception("Portfolio "+portfolioId+" doesn't exists");
        }
        return pf;
    }
}

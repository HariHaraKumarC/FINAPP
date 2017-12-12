package hari.app.finapp.controllers;

import hari.app.finapp.models.Savings;
import hari.app.finapp.services.SavingsService;
import hari.app.finapp.utils.FinappCustomResponseMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by HariHaraKumar on 11/12/2017.
 */
@RestController
public class SavingsController {

    @Autowired
    SavingsService savingsService;

    /**
     * Create new savings
     * @param savings
     * @return ResponseEntity
     */
    @RequestMapping(value = "/savings/create",method= RequestMethod.POST)
    public ResponseEntity<FinappCustomResponseMessageHandler> createSavings(@RequestBody Savings savings){
        savingsService.saveSavings(savings);
        return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("Savings Account successfully created"), HttpStatus.OK);
    }

    /**
     * Update Savings
     * @param savings
     * @return ResponseEntity
     */
    @RequestMapping(value = "/savings/update",method= RequestMethod.PUT)
    public ResponseEntity<FinappCustomResponseMessageHandler> updateSavings(@RequestBody Savings savings){
        if(savingsService.findById(savings.getSavingsId())!=null){
            savingsService.updateSavings(savings);
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("Savings Account successfully updated"), HttpStatus.OK);
        }else{
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("Savings Account doesn't exists"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete savings
     * @param savingsId
     * @return ResponseEntity
     */
    @RequestMapping(value = "/savings/delete",method= RequestMethod.DELETE)
    public ResponseEntity<FinappCustomResponseMessageHandler> deleteSavings(@RequestParam("savingsId") long savingsId){
        if(savingsService.findById(savingsId)!=null){
            savingsService.deleteSavingsById(savingsId);
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("Savings Account successfully deleted"), HttpStatus.OK);
        }else{
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("Savings Account doesn't exists"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get Savings Details
     * @param savingsId
     * @return ResponseEntity
     */
    @RequestMapping(value = "/savings/details",method= RequestMethod.GET)
    public ResponseEntity<?> getSavingsDetails(@RequestParam("savingsId") long savingsId){
        Savings savings=savingsService.findById(savingsId);
        if(savings==null){
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("Savings Account doesn't exists"), HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<Savings>(savings,HttpStatus.OK);
        }
    }
}

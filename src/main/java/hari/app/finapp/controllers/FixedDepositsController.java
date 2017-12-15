package hari.app.finapp.controllers;

import hari.app.finapp.models.FixedDeposits;
import hari.app.finapp.services.FixedDepositsService;
import hari.app.finapp.utils.FinappCustomResponseMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by HariHaraKumar on 11/12/2017.
 */
@RestController
public class FixedDepositsController {

    @Autowired
    FixedDepositsService fixedDepositsService;

    /**
     * Create new fixed deposits holding account (that contains the summary of the all FD transactions) for the user
     * @param fixedDeposits
     * @return ResponseEntity
     */
    @RequestMapping(value = "/fixedDeposits/create",method= RequestMethod.POST)
    public ResponseEntity<FinappCustomResponseMessageHandler> createFixedDepositHoldingAccount(@RequestBody FixedDeposits fixedDeposits){
        fixedDepositsService.saveFixedDeposits(fixedDeposits);
        return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("Fixed Deposits Holding Account successfully created"), HttpStatus.OK);
    }

    /**
     * Update fixed deposits holding account
     * @param fixedDeposits
     * @return ResponseEntity
     */
    @RequestMapping(value = "/fixedDeposits/update",method= RequestMethod.PUT)
    public ResponseEntity<FinappCustomResponseMessageHandler> updateFixedDepositHoldingAccount(@RequestBody FixedDeposits fixedDeposits){
        if(fixedDepositsService.findById(fixedDeposits.getFixedDepositsId())!=null){
            fixedDepositsService.updateFixedDeposits(fixedDeposits);
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("Fixed Deposits Holding Account successfully updated"), HttpStatus.OK);
        }else{
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("Fixed Deposits Holding Account doesn't exists"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete fixed deposits holding account
     * @param fixedDepositsId
     * @return ResponseEntity
     */
    @RequestMapping(value = "/fixedDeposits/delete",method= RequestMethod.DELETE)
    public ResponseEntity<FinappCustomResponseMessageHandler> deleteSavings(@RequestParam("fixedDepositsId") long fixedDepositsId){
        if(fixedDepositsService.findById(fixedDepositsId)!=null){
            fixedDepositsService.deleteFixedDepositsById(fixedDepositsId);
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("Fixed Deposits Holding Account successfully deleted"), HttpStatus.OK);
        }else{
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("Fixed Deposits Holding Account doesn't exists"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get fixed deposits summary
     * @param fixedDepositsId
     * @return ResponseEntity
     */
    @RequestMapping(value = "/fixedDeposits/details",method= RequestMethod.GET)
    public ResponseEntity<?> getSavingsDetails(@RequestParam("fixedDepositsId") long fixedDepositsId){
        FixedDeposits fixedDeposits=fixedDepositsService.findById(fixedDepositsId);
        if(fixedDeposits==null){
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("Fixed Deposits Holding Account doesn't exists"), HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<FixedDeposits>(fixedDeposits,HttpStatus.OK);
        }
    }
}

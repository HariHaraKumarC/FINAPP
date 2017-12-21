package hari.app.finapp.controllers;

import hari.app.finapp.exceptions.FinappException;
import hari.app.finapp.models.FDTransactions;
import hari.app.finapp.services.FDTransactionsService;
import hari.app.finapp.utils.FinappCustomResponseMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by HariHaraKumar on 21/12/2017.
 */

@RestController
public class FDTransactionsController {

    @Autowired
    FDTransactionsService fdTransactionsService;

    /**
     * Create new FD Transaction
     * @param fdTransactions
     * @return ResponseEntity
     */
    @RequestMapping(value = "/fdTransactions/create",method= RequestMethod.POST)
    public ResponseEntity<FinappCustomResponseMessageHandler> createFDTransaction(@RequestBody FDTransactions fdTransactions){
        try {
            fdTransactionsService.saveFDTransactions(fdTransactions);
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("FD Transactions created successfully"), HttpStatus.OK);
        }catch (FinappException fe){
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler(fe.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update FD Transaction
     * @param fdTransactions
     * @return ResponseEntity
     */
    @RequestMapping(value = "/fdTransactions/update",method= RequestMethod.PUT)
    public ResponseEntity<FinappCustomResponseMessageHandler> updateFDTransaction(@RequestBody FDTransactions fdTransactions){
        if(fdTransactionsService.findById(fdTransactions.getFdTransactionsId())!=null){
            try {
                fdTransactionsService.updateFDTransactions(fdTransactions);
                return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("FD Transactions updated successfully"), HttpStatus.OK);
            }catch (FinappException fe){
                return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler(fe.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("FD Transaction doesn't exists"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete FD Transaction
     * @param fdTransactionId
     * @return ResponseEntity
     */
    @RequestMapping(value = "/fdTransactions/delete",method= RequestMethod.DELETE)
    public ResponseEntity<FinappCustomResponseMessageHandler> deleteFDTransaction(@RequestParam("fdTransactionId") long fdTransactionId){
        if(fdTransactionsService.findById(fdTransactionId)!=null){
            fdTransactionsService.deleteFDTransactionsById(fdTransactionId);
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("FD Transactions deleted successfully"), HttpStatus.OK);
        }else{
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("FD Transaction doesn't exists"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get FD Transaction Details
     * @param fdTransactionId
     * @return ResponseEntity
     */
    @RequestMapping(value = "/fdTransactions/details",method= RequestMethod.GET)
    public ResponseEntity<?> getSavingsDetails(@RequestParam("fdTransactionId") long fdTransactionId){
        FDTransactions fdTransactions=fdTransactionsService.findById(fdTransactionId);
        if(fdTransactions==null){
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("FD Transaction doesn't exists"), HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<FDTransactions>(fdTransactions,HttpStatus.OK);
        }
    }
}

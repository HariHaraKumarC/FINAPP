package hari.app.finapp.controllers.calculators;

import hari.app.finapp.exceptions.FinappException;
import hari.app.finapp.models.FDTransactions;
import hari.app.finapp.services.calculators.FDCalculatorService;
import hari.app.finapp.utils.FinappCustomResponseMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HariHaraKumar on 19/12/2017.
 */

@RestController
public class FDCalculatorController {

    @Autowired
    FDCalculatorService fdCalculatorService;

    @RequestMapping(value = "/fdCalculator/calculate",method= RequestMethod.PUT)
    public ResponseEntity<?> calculateFixedDeposit(@RequestBody FDTransactions fdTransactions){
        try {
            fdCalculatorService.calculateFD(fdTransactions);
            return new ResponseEntity<FDTransactions>(fdTransactions, HttpStatus.OK);
        }catch(FinappException fe){
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler(fe.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

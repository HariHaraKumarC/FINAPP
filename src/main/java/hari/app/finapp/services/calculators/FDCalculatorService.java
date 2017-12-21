package hari.app.finapp.services.calculators;

import hari.app.finapp.exceptions.FinappException;
import hari.app.finapp.models.FDTransactions;

/**
 * Created by HariHaraKumar on 18/12/2017.
 */
public interface FDCalculatorService {

    /**
     * Calculates the maturity amount, current value, interest accrued, interest earned and received.
     * @param fdTransactions
     * @throws FinappException
     */
    void calculateFD(FDTransactions fdTransactions)throws FinappException;
}

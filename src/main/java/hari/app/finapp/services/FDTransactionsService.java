package hari.app.finapp.services;

import hari.app.finapp.exceptions.FinappException;
import hari.app.finapp.models.FDTransactions;

import java.util.List;

/**
 * Created by HariHaraKumar on 18/12/2017.
 */
public interface FDTransactionsService {

    void saveFDTransactions(FDTransactions fdTransactions) throws FinappException;

    void updateFDTransactions(FDTransactions fdTransactions) throws FinappException;

    void deleteFDTransactionsById(long fdTransactionsId);

    FDTransactions findById(long fdTransactionsId);

    List<FDTransactions> listAllFDTransactions(Long fixedDepositsId);
}

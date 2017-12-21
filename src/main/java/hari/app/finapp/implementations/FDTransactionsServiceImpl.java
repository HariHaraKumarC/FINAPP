package hari.app.finapp.implementations;

import hari.app.finapp.constants.FDConstants;
import hari.app.finapp.exceptions.FinappException;
import hari.app.finapp.models.FDTransactions;
import hari.app.finapp.models.FixedDeposits;
import hari.app.finapp.models.Savings;
import hari.app.finapp.repositories.FDTransactionsRepository;
import hari.app.finapp.repositories.FixedDepositsRepository;
import hari.app.finapp.services.FDTransactionsService;
import hari.app.finapp.services.FixedDepositsService;
import hari.app.finapp.services.calculators.FDCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by HariHaraKumar on 18/12/2017.
 */

@Service("fdTransactionsService")
public class FDTransactionsServiceImpl implements FDTransactionsService {

    @Autowired
    FDTransactionsRepository fdTransactionsRepo;

    @Autowired
    FDCalculatorService fdCalculatorService;

    @Override
    @Transactional
    public void saveFDTransactions(FDTransactions fdTransactions) throws FinappException {
        fdCalculatorService.calculateFD(fdTransactions);
        updateParentTables(fdTransactions, FDConstants.FD_OPCODE_CREATE);
        fdTransactionsRepo.save(fdTransactions);
    }

    @Override
    @Transactional
    public void updateFDTransactions(FDTransactions fdTransactions) throws FinappException {
        FDTransactions fdTransactionsCopy = fdTransactions; //Local copy
        fdCalculatorService.calculateFD(fdTransactions);
        updateParentTables(fdTransactionsCopy, FDConstants.FD_OPCODE_DELETE);
        updateParentTables(fdTransactions, FDConstants.FD_OPCODE_CREATE);
        fdTransactionsRepo.save(fdTransactions);
    }

    @Override
    @Transactional
    public void deleteFDTransactionsById(long fdTransactionsId) {
        updateParentTables(findById(fdTransactionsId), FDConstants.FD_OPCODE_DELETE);
        fdTransactionsRepo.delete(fdTransactionsId);
    }

    @Override
    public FDTransactions findById(long fdTransactionsId) {
        return fdTransactionsRepo.findOne(fdTransactionsId);
    }

    @Override
    public List<FDTransactions> listAllFDTransactions(Long fixedDepositsId) {
        return fdTransactionsRepo.findAllByFixedDepositsFixedDepositsId(fixedDepositsId);
    }

    /**
     * Update the Parent tables (Fixed_Deposits & Savings table)
     *
     * @param fdTransactions
     * @param operationCode  (CREATE or DELETE)
     */
    private void updateParentTables(FDTransactions fdTransactions, int operationCode) {
        updateFixedDepositsTable(fdTransactions, operationCode);
        updateSavingsTable(fdTransactions, operationCode);
    }

    /**
     * Update the Fixed Deposit table
     *
     * @param fdTransactions
     * @param operationCode
     */
    private void updateFixedDepositsTable(FDTransactions fdTransactions, int operationCode) {
        FixedDeposits fixedDeposits = fdTransactions.getFixedDeposits();
        double newPrincipalAmount = getNewParentTableValues(fixedDeposits.getPrincipalAmount(), fdTransactions.getPrincipalAmount(), operationCode);
        double newCurrentValue = getNewParentTableValues(fixedDeposits.getCurrentValue(), fdTransactions.getCurrentValue(), operationCode);
        double newInterestEarned = getNewParentTableValues(fixedDeposits.getInterestEarned(), fdTransactions.getInterestEarned(), operationCode);
        double newInterestAccrued = getNewParentTableValues(fixedDeposits.getInterestAccrued(), fdTransactions.getInterestAccrued(), operationCode);
        double newInterestReceived = getNewParentTableValues(fixedDeposits.getInterestReceived(), fdTransactions.getInterestReceived(), operationCode);
        fixedDeposits.setPrincipalAmount(newPrincipalAmount);
        fixedDeposits.setCurrentValue(newCurrentValue);
        fixedDeposits.setInterestEarned(newInterestEarned);
        fixedDeposits.setInterestAccrued(newInterestAccrued);
        fixedDeposits.setInterestReceived(newInterestReceived);
        fdTransactions.setFixedDeposits(fixedDeposits);
    }

    /**
     * Update the Savings table
     *
     * @param fdTransactions
     * @param operationCode
     */
    private void updateSavingsTable(FDTransactions fdTransactions, int operationCode) {
        FixedDeposits fixedDeposits = fdTransactions.getFixedDeposits();
        Savings savings = fixedDeposits.getSavings();
        double newPrincipalAmount = getNewParentTableValues(savings.getPrincipalAmount(), fixedDeposits.getPrincipalAmount(), operationCode);
        double newCurrentValue = getNewParentTableValues(savings.getCurrentValue(), fixedDeposits.getCurrentValue(), operationCode);
        double newInterestEarned = getNewParentTableValues(savings.getInterestEarned(), fixedDeposits.getInterestEarned(), operationCode);
        double newInterestAccrued = getNewParentTableValues(savings.getInterestAccrued(), fixedDeposits.getInterestAccrued(), operationCode);
        double newInterestReceived = getNewParentTableValues(savings.getInterestReceived(), fixedDeposits.getInterestReceived(), operationCode);
        savings.setPrincipalAmount(newPrincipalAmount);
        savings.setCurrentValue(newCurrentValue);
        savings.setInterestEarned(newInterestEarned);
        savings.setInterestAccrued(newInterestAccrued);
        savings.setInterestReceived(newInterestReceived);
        fixedDeposits.setSavings(savings);
        fdTransactions.setFixedDeposits(fixedDeposits);
    }

    /**
     * Calculates the new values to be populated in the parent table
     *
     * @param baseValue
     * @param augend
     * @param opCode
     * @return double
     */
    private double getNewParentTableValues(double baseValue, double augend, int opCode) {
        BigDecimal base = new BigDecimal(baseValue);
        BigDecimal aug = new BigDecimal(augend);
        double result = 0.0;
        if (opCode == FDConstants.FD_OPCODE_CREATE) {
            result = base.add(aug).doubleValue();
        } else if (opCode == FDConstants.FD_OPCODE_DELETE) {
            result = base.subtract(aug).doubleValue();
        }
        return result;
    }
}

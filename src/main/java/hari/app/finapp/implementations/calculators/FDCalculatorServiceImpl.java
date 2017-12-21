package hari.app.finapp.implementations.calculators;

import hari.app.finapp.constants.FDConstants;
import hari.app.finapp.exceptions.FinappException;
import hari.app.finapp.models.FDTransactions;
import hari.app.finapp.services.calculators.FDCalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Created by HariHaraKumar on 18/12/2017.
 */
@Service("fdCalculatorService")
public class FDCalculatorServiceImpl implements FDCalculatorService {

    private static final Logger LOG = LoggerFactory.getLogger(FDCalculatorServiceImpl.class);

    private BigDecimal principalAmount;
    private Date startDate;
    private Date maturityDate;
    private BigDecimal interestRate;
    private int interestPayoutFrequency;
    private int interestReInvestmentFrequency;
    private BigDecimal actualTerm;
    private BigDecimal numberOfCompoundingTimes;
    private Date currentDate;
    private BigDecimal currentTerm;
    private BigDecimal totalInterest;
    private BigDecimal maturityAmount;
    private BigDecimal currentValue;
    private BigDecimal interestReceived;
    private BigDecimal interestEarned;
    private BigDecimal interestAccrued;

    @Override
    public void calculateFD(FDTransactions fdTransactions) throws FinappException {
        initializeFDInputParams(fdTransactions);
        setActiveStatus(fdTransactions);
        calculateMaturityAndInterestDetails(fdTransactions);
        updateFDOutputParams(fdTransactions);
        LOG.debug(this.toString());
    }

    /**
     * Initialize the Fixed Deposits input parameters
     *
     * @param fdTransactions
     * @throws FinappException
     */
    private void initializeFDInputParams(FDTransactions fdTransactions) throws FinappException {
        LOG.info("Started Initialising FD Input Parameters");
        principalAmount = new BigDecimal(fdTransactions.getPrincipalAmount());
        startDate = fdTransactions.getStartDate();
        maturityDate = fdTransactions.getMaturityDate();
        interestRate = new BigDecimal(fdTransactions.getInterestRate());
        interestRate = interestRate.divide(new BigDecimal("100"), FDConstants.DEFAULT_BIG_DECIMAL_DIVISION_SCALE, BigDecimal.ROUND_HALF_UP);
        interestPayoutFrequency = fdTransactions.getInterestPayoutFrequency();
        interestReInvestmentFrequency = fdTransactions.getInterestReInvestmentFrequency();
        currentDate = new Date();
        actualTerm = getTerm(startDate, maturityDate);
        currentTerm = getTerm(startDate, currentDate);
        calculateFDCompoundingTimes();
        LOG.info("Completed Initialising FD Input Parameters");
    }

    /**
     * Calculates the term in years
     *
     * @param sDate
     * @param eDate
     * @return
     * @throws FinappException
     */
    private BigDecimal getTerm(Date sDate, Date eDate) throws FinappException {
        BigDecimal result = null;
        if (isValidStartAndMaturityDate(sDate, eDate)) {
            LocalDate lsDate = dateToLocalDate(sDate);
            LocalDate leDate = dateToLocalDate(eDate);
            Period period = Period.between(lsDate, leDate);
            if (period.getDays() > 0) {
                BigDecimal daysBetween = new BigDecimal(ChronoUnit.DAYS.between(lsDate, leDate));
                result = daysBetween.divide(new BigDecimal("365"), FDConstants.DEFAULT_BIG_DECIMAL_DIVISION_SCALE, BigDecimal.ROUND_HALF_UP);
            } else if (period.getDays() == 0 && period.getMonths() > 0) {
                BigDecimal monthsBetween = new BigDecimal(ChronoUnit.MONTHS.between(lsDate, leDate));
                result = monthsBetween.divide(new BigDecimal("12"), FDConstants.DEFAULT_BIG_DECIMAL_DIVISION_SCALE, BigDecimal.ROUND_HALF_UP);
            } else {
                result = new BigDecimal(period.getYears());
            }
        }
        return result;
    }

    private void calculateMaturityAndInterestDetails(FDTransactions fdTransactions) throws FinappException {
        LOG.info("Started Calculating FD Interest and Maturity Values");
        if (isInterestPayoutfrequencyOptionEnabled(fdTransactions.getInterestPayoutFrequency())) {
            throw new FinappException("Currently FINAPP API supports only, Interest paid out on maturity");
        } else {
            totalInterest = calculateInterestAmount(actualTerm);
            maturityAmount = getPrincipalPlusInterest(totalInterest);
            interestReceived = new BigDecimal("0");
            interestAccrued = calculateInterestAmount(currentTerm);
            interestEarned = interestReceived.add(interestAccrued);
            currentValue = getPrincipalPlusInterest(interestEarned);
        }
        LOG.info("Completed Calculating FD Interest and Maturity Values");
    }

    /**
     * Calculates the interest amount
     *
     * @param period
     * @return BigDecimal
     */
    private BigDecimal calculateInterestAmount(BigDecimal period) {
        BigDecimal interest;
        if (numberOfCompoundingTimes.intValue() == 0 || isTermBelowCompoundingFrequencyInterval(period)) {
            interest = getSimpleInterest(period, interestRate, principalAmount);
        } else {
            interest = getCompoundInterest(period, interestRate, principalAmount, numberOfCompoundingTimes);
        }
        interest = interest.setScale(FDConstants.DEFAULT_BIG_DECIMAL_SCALE, BigDecimal.ROUND_HALF_UP);
        return interest;
    }

    /**
     * Returns Prinicipal Plus Interest Amount
     *
     * @param interest
     * @return BigDecimal
     */
    private BigDecimal getPrincipalPlusInterest(BigDecimal interest) {
        BigDecimal result = principalAmount.add(interest);
        result = result.setScale(FDConstants.DEFAULT_BIG_DECIMAL_SCALE, BigDecimal.ROUND_HALF_UP);
        return result;
    }


    /**
     * Calculates the compound interest SI=Prt
     *
     * @param period
     * @param rateOfInterest
     * @param principal
     * @return BigDecimal
     */
    private BigDecimal getSimpleInterest(BigDecimal period, BigDecimal rateOfInterest, BigDecimal principal) {
        BigDecimal simpleInterest = period.multiply(rateOfInterest);
        simpleInterest = simpleInterest.multiply(principal);
        return simpleInterest;
    }

    /**
     * Calculates the compound interest CI=P(1+(r/n))^nt
     *
     * @param period
     * @param rateOfInterest
     * @param principal
     * @param numberOfCompoundingTimes
     * @return
     */
    private BigDecimal getCompoundInterest(BigDecimal period, BigDecimal rateOfInterest, BigDecimal principal, BigDecimal numberOfCompoundingTimes) {
        BigDecimal compoundInterest = rateOfInterest.divide(numberOfCompoundingTimes, FDConstants.DEFAULT_BIG_DECIMAL_DIVISION_SCALE, BigDecimal.ROUND_HALF_UP);
        compoundInterest = compoundInterest.add(new BigDecimal("1"));
        BigDecimal exponent = numberOfCompoundingTimes.multiply(period);
        compoundInterest = new BigDecimal(Math.pow(compoundInterest.doubleValue(), exponent.doubleValue()));
        compoundInterest = compoundInterest.multiply(principal);
        compoundInterest = compoundInterest.subtract(principal);
        return compoundInterest;
    }

    /**
     * Checks whether the term is less than the compounding frequency interval
     *
     * @param period
     * @return boolean
     */
    private boolean isTermBelowCompoundingFrequencyInterval(BigDecimal period) {
        int numOfCompoundingTimes = numberOfCompoundingTimes.intValue();
        double term = period.doubleValue();
        boolean result = false;
        if (numOfCompoundingTimes == 1 && term < 1) { //Term less than a year and compounding frequency is annually
            result = true;
        } else if (numOfCompoundingTimes == 2 && term < 0.5) { //Term less than six months and compounding frequency is half yearly
            result = true;
        } else if (numOfCompoundingTimes == 3 && term < 0.25) { //Term less than six months and compounding frequency is quarterly
            result = true;
        } else if (numOfCompoundingTimes == 4 && term < (1 / 12)) { //Term less than six months and compounding frequency is monthly
            result = true;
        }
        return result;
    }

    /**
     * Calculate the fixed deposits compounding times in a year (n)
     *
     * @throws FinappException
     */
    private void calculateFDCompoundingTimes() throws FinappException {
        switch (interestReInvestmentFrequency) {
            case FDConstants.FD_INTEREST_RE_INVESTMENT_FREQUENCY_DISABLED:
                numberOfCompoundingTimes = new BigDecimal("0");
                break;
            case FDConstants.FD_INTEREST_RE_INVESTMENT_FREQUENCY_MONTHLY:
                numberOfCompoundingTimes = new BigDecimal("12");
                break;
            case FDConstants.FD_INTEREST_RE_INVESTMENT_FREQUENCY_QUARTERLY:
                numberOfCompoundingTimes = new BigDecimal("4");
                break;
            case FDConstants.FD_INTEREST_RE_INVESTMENT_FREQUENCY_HALF_YEARLY:
                numberOfCompoundingTimes = new BigDecimal("2");
                break;
            case FDConstants.FD_INTEREST_RE_INVESTMENT_FREQUENCY_YEARLY:
                numberOfCompoundingTimes = new BigDecimal("1");
                break;
            default:
                throw new FinappException("Invalid Compounding frequency");
        }
    }

    /**
     * Checks for the validity of start and maturity date
     *
     * @param sDate
     * @param mDate
     * @return boolean
     * @throws FinappException
     */
    private boolean isValidStartAndMaturityDate(Date sDate, Date mDate) throws FinappException {
        boolean isValidDates = false;
        if (sDate == null || mDate == null) {
            throw new FinappException("Start Date or Maturity Date cannot be null");
        } else if (mDate.before(sDate)) {
            throw new FinappException("Maturity Date should be after start date");
        } else {
            isValidDates = true;
        }
        return isValidDates;
    }

    /**
     * Set the FD Status to ACTIVE
     *
     * @param fdTransactions
     */
    private void setActiveStatus(FDTransactions fdTransactions) {
        LOG.info("Setting FD Status to ACTIVE");
        fdTransactions.setStatus(FDConstants.FD_STATUS_ACTIVE);
    }

    /**
     * Checks for Interest payout frequency option
     *
     * @param interestPayoutFrequency
     * @return boolean
     */
    private boolean isInterestPayoutfrequencyOptionEnabled(int interestPayoutFrequency) {
        return interestPayoutFrequency != FDConstants.FD_INTEREST_PAYOUT_FREQUENCY_DISABLED;
    }

    /**
     * Convert Java.util.Date to java.time.LocalDate
     *
     * @param date
     * @return LocalDate
     */
    private LocalDate dateToLocalDate(Date date) {
        LocalDate localDate = null;
        if (date != null) {
            localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        return localDate;
    }

    /**
     * Updates the fixed deposit output parameters to the collection
     */
    private void updateFDOutputParams(FDTransactions fdTransactions) {
        LOG.info("Started Updating the result to the entity object");
        fdTransactions.setMaturityAmount(maturityAmount.doubleValue());
        fdTransactions.setCurrentValue(currentValue.doubleValue());
        fdTransactions.setInterestEarned(interestEarned.doubleValue());
        fdTransactions.setInterestAccrued(interestAccrued.doubleValue());
        fdTransactions.setInterestReceived(interestReceived.doubleValue());
        LOG.info("Completed Updating the result to the entity object");
    }

    @Override
    public String toString() {
        return "FDCalculatorService{" +
                "principalAmount=" + principalAmount +
                ", startDate=" + startDate +
                ", maturityDate=" + maturityDate +
                ", interestRate=" + interestRate +
                ", interestPayoutFrequency=" + interestPayoutFrequency +
                ", interestReInvestmentFrequency=" + interestReInvestmentFrequency +
                ", actualTerm=" + actualTerm +
                ", numberOfCompoundingTimes=" + numberOfCompoundingTimes +
                ", currentDate=" + currentDate +
                ", currentTerm=" + currentTerm +
                ", totalInterest=" + totalInterest +
                ", maturityAmount=" + maturityAmount +
                ", currentValue=" + currentValue +
                ", interestReceived=" + interestReceived +
                ", interestEarned=" + interestEarned +
                ", interestAccrued=" + interestAccrued +
                '}';
    }
}

package hari.app.finapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by HariHaraKumar on 15/12/2017.
 */
@Entity
@Table(name = "fdTransactions")
public class FDTransactions implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fdTransactionsId",nullable = false)
    private long fdTransactionsId;

    @Column(name = "status")
    private int status;

    @Column(name = "accountNumber")
    private String accountNumber;

    @Column(name = "financialInstitutionName")
    private String financialInstitutionName;

    @Column(name = "startDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private Date startDate;

    @Column(name = "maturityDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private Date maturityDate;

    @Column(name = "interestPayoutFrequency")
    private int interestPayoutFrequency;

    @Column(name = "interestReInvestmentFrequency")
    private int interestReInvestmentFrequency;

    @Column(name = "interestRate")
    private double interestRate;

    @Column(name = "principalAmount")
    private double principalAmount;

    @Column(name = "currentValue")
    private double currentValue;

    @Column(name = "maturityAmount")
    private double maturityAmount;

    @Column(name = "interestAccrued")
    private double interestAccrued;

    @Column(name = "interestEarned")
    private double interestEarned;

    @Column(name = "interestReceived")
    private double interestReceived;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fixedDepositsId")
    @JsonBackReference
    private FixedDeposits fixedDeposits;

    public FDTransactions(){
    }

    public long getFdTransactionsId() {
        return fdTransactionsId;
    }

    public void setFdTransactionsId(long fdTransactionsId) {
        this.fdTransactionsId = fdTransactionsId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFinancialInstitutionName() {
        return financialInstitutionName;
    }

    public void setFinancialInstitutionName(String financialInstitutionName) {
        this.financialInstitutionName = financialInstitutionName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(Date maturityDate) {
        this.maturityDate = maturityDate;
    }

    public int getInterestPayoutFrequency() {
        return interestPayoutFrequency;
    }

    public void setInterestPayoutFrequency(int interestPayoutFrequency) {
        this.interestPayoutFrequency = interestPayoutFrequency;
    }

    public int getInterestReInvestmentFrequency() {
        return interestReInvestmentFrequency;
    }

    public void setInterestReInvestmentFrequency(int interestReInvestmentFrequency) {
        this.interestReInvestmentFrequency = interestReInvestmentFrequency;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(double principalAmount) {
        this.principalAmount = principalAmount;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public double getMaturityAmount() {
        return maturityAmount;
    }

    public void setMaturityAmount(double maturityAmount) {
        this.maturityAmount = maturityAmount;
    }

    public double getInterestAccrued() {
        return interestAccrued;
    }

    public void setInterestAccrued(double interestAccrued) {
        this.interestAccrued = interestAccrued;
    }

    public double getInterestEarned() {
        return interestEarned;
    }

    public void setInterestEarned(double interestEarned) {
        this.interestEarned = interestEarned;
    }

    public double getInterestReceived() {
        return interestReceived;
    }

    public void setInterestReceived(double interestReceived) {
        this.interestReceived = interestReceived;
    }

    public FixedDeposits getFixedDeposits() {
        return fixedDeposits;
    }

    public void setFixedDeposits(FixedDeposits fixedDeposits) {
        this.fixedDeposits = fixedDeposits;
    }
}

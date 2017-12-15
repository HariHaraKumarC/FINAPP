package hari.app.finapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by HariHaraKumar on 13/12/2017.
 */
@Entity
@Table(name = "fixedDeposits")
public class FixedDeposits implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fixedDepositsId",nullable = false)
    private long fixedDepositsId;

    @Column(name = "principalAmount")
    private double principalAmount;

    @Column(name = "currentValue")
    private double currentValue;

    @Column(name = "interestAccrued")
    private double interestAccrued;

    @Column(name = "interestEarned")
    private double interestEarned;

    @Column(name = "interestReceived")
    private double interestReceived;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "savingsId")
    @JsonBackReference
    private Savings savings;

    @OneToOne(mappedBy = "fixedDeposits",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    private FDTransactions fdTransactions;

    public FixedDeposits(){
    }

    public FixedDeposits(Savings savings){
        this.savings=savings;
    }

    public long getFixedDepositsId() {
        return fixedDepositsId;
    }

    public void setFixedDepositsId(long fixedDepositsId) {
        this.fixedDepositsId = fixedDepositsId;
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

    public Savings getSavings() {
        return savings;
    }

    public void setSavings(Savings savings) {
        this.savings = savings;
    }

    public FDTransactions getFdTransactions() {
        return fdTransactions;
    }

    public void setFdTransactions(FDTransactions fdTransactions) {
        this.fdTransactions = fdTransactions;
    }
}

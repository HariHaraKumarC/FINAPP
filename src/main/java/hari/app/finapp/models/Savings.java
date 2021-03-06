package hari.app.finapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by HariHaraKumar on 8/12/2017.
 */
@Entity
@Table (name = "savings")
public class Savings implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "savingsId",nullable = false)
    private long savingsId;

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
    @JoinColumn(name = "portfolioId")
    @JsonBackReference
    private Portfolio portfolio;

    @OneToOne(mappedBy = "savings",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private FixedDeposits fixedDeposits;

    public Savings(){
    }

    public Savings(Portfolio portfolio){
        this.portfolio=portfolio;
    }

    public long getSavingsId() {
        return savingsId;
    }

    public void setSavingsId(long savingsId) {
        this.savingsId = savingsId;
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

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public FixedDeposits getFixedDeposits() {
        return fixedDeposits;
    }

    public void setFixedDeposits(FixedDeposits fixedDeposits) {
        this.fixedDeposits = fixedDeposits;
    }
}

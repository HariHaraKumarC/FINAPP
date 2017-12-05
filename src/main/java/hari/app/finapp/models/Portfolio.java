package hari.app.finapp.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by HariHaraKumar on 11/30/2017.
 */
@Entity
@Table(name = "portfolio")
public class Portfolio implements Serializable {

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "portfolioId")
    private long portfolioId;

    @Column(name = "name")
    private String name;

    @Column(name = "investmentAmount")
    private double investmentAmount;

    @Column(name = "realizedGainLossAmount")
    private double realizedGainLossAmount;

    //Used by Spring JPA
    protected Portfolio(){
    }

    public long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(double investmentAmount) {
        this.investmentAmount = investmentAmount;
    }

    public double getRealizedGainLossAmount() {
        return realizedGainLossAmount;
    }

    public void setRealizedGainLossAmount(double realizedGainLossAmount) {
        this.realizedGainLossAmount = realizedGainLossAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString(){
        return String.format("Portfolio[portfolioId='%s', name='%s', investmentAmount=%f, realizedGainLossAmount=%f]",portfolioId,name,investmentAmount,realizedGainLossAmount);
    }
}

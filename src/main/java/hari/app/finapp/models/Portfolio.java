package hari.app.finapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by HariHaraKumar on 11/30/2017.
 */
@Entity
@Table(name = "portfolio")
public class Portfolio implements Serializable {

    @Column(name = "userId")
    private String userId;

    @Id
    @Column(name = "portfolioId")
    private Long portfolioId;

    @Column(name = "name")
    private String name;

    @Column(name = "investmentAmount")
    private double investmentAmount;

    @Column(name = "realizedGainLossAmount")
    private double realizedGainLossAmount;

    //Used by Spring JPA
    protected Portfolio(){
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String toString(){
        return String.format("Portfolio[userId='%s, portfolioId='%s', name='%s', investmentAmount=%d, realizedGainLossAmount=%d]",userId,portfolioId,name,investmentAmount,realizedGainLossAmount);
    }
}

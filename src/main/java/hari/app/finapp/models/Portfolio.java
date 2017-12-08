package hari.app.finapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by HariHaraKumar on 11/30/2017.
 */
@Entity
@Table(name = "portfolio")
public class Portfolio implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @JsonBackReference
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "portfolioId",nullable = false)
    private long portfolioId;

    @Column(name = "name",nullable = false)
    private String name;

    @OneToOne(mappedBy = "portfolio",fetch = FetchType.LAZY )
    @JsonManagedReference
    private Savings savings;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Savings getSavings() {
        return savings;
    }

    public void setSavings(Savings savings) {
        this.savings = savings;
    }
}

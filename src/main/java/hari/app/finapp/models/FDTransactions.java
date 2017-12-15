package hari.app.finapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    private int status;

    private String accountNumber;

    private String financialInstitutionName;

    private Date startDate;

    private Date maturityDate;

    private int interestPayoutFrequency;

    private int interestReInvestmentFrequency;

    private double interestRate;

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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fixedDepositsId")
    @JsonBackReference
    private FixedDeposits fixedDeposits;

    public FDTransactions(){
    }
}

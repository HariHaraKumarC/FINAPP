package hari.app.finapp.services;

import hari.app.finapp.models.Savings;

/**
 * Created by HariHaraKumar on 8/12/2017.
 */
public interface SavingsService {

    void saveSavings(Savings savings);

    void updateSavings(Savings savings);

    void deleteSavingsById(long savingsId);

    Savings findById(long savingsId);
}

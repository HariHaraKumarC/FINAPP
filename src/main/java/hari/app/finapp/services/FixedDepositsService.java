package hari.app.finapp.services;

import hari.app.finapp.models.FixedDeposits;

/**
 * Created by HariHaraKumar on 8/12/2017.
 */
public interface FixedDepositsService {

    void saveFixedDeposits(FixedDeposits fixedDeposits);

    void updateFixedDeposits(FixedDeposits fixedDeposits);

    void deleteFixedDepositsById(long fixedDepositsId);

    FixedDeposits findById(long fixedDepositsId);
}

package hari.app.finapp.implementations;

import hari.app.finapp.models.Savings;
import hari.app.finapp.repositories.SavingsRepository;
import hari.app.finapp.services.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HariHaraKumar on 8/12/2017.
 */

@Service("savingsService")
public class SavingsServiceImpl implements SavingsService {

    @Autowired
    SavingsRepository savingsRepo;

    @Override
    public void saveSavings(Savings savings) {
        savingsRepo.save(savings);
    }

    @Override
    public void updateSavings(Savings savings) {
        saveSavings(savings);
    }
}

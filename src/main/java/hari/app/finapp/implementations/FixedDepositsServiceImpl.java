package hari.app.finapp.implementations;

import hari.app.finapp.models.FixedDeposits;
import hari.app.finapp.repositories.FixedDepositsRepository;
import hari.app.finapp.services.FixedDepositsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HariHaraKumar on 8/12/2017.
 */

@Service("fixedDepositsService")
public class FixedDepositsServiceImpl implements FixedDepositsService {

    @Autowired
    FixedDepositsRepository fixedDepositsRepo;

    @Override
    public void saveFixedDeposits(FixedDeposits fixedDeposits) {
        fixedDepositsRepo.save(fixedDeposits);
    }

    @Override
    public void updateFixedDeposits(FixedDeposits fixedDeposits) {
        saveFixedDeposits(fixedDeposits);
    }

    @Override
    public void deleteFixedDepositsById(long fixedDepositsId) {
        fixedDepositsRepo.delete(fixedDepositsId);
    }

    @Override
    public FixedDeposits findById(long fixedDepositsId) {
        return fixedDepositsRepo.findOne(fixedDepositsId);
    }
}

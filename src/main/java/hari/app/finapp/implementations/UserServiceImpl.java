package hari.app.finapp.implementations;

import hari.app.finapp.models.User;
import hari.app.finapp.repositories.UserRepository;
import hari.app.finapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HariHaraKumar on 12/5/2017.
 */
@Service("uerService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    @Override
    public void saveUser(User user) {
        userRepo.save(user);
    }

    @Override
    public void updateUser(User user) {
        saveUser(user);
    }

    @Override
    public void deleteUserById(long userId) {
        userRepo.delete(userId);
    }

    @Override
    public User findUserById(long userId) {
        return userRepo.findOne(userId);
    }

    @Override
    public boolean isUserExists(User user) {
        return userRepo.findByEmail(user.getEmail())!=null;
    }
}

package hari.app.finapp.services;

import hari.app.finapp.models.User;

/**
 * Created by HariHaraKumar on 12/5/2017.
 */
public interface UserService {

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(long userId);

    User findUserById(long userId);

    boolean isUserExists(User user);
}

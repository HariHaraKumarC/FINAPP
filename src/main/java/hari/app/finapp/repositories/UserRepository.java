package hari.app.finapp.repositories;

import hari.app.finapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by HariHaraKumar on 5/12/2017.
 */
public interface UserRepository extends JpaRepository<User,Long> {

}

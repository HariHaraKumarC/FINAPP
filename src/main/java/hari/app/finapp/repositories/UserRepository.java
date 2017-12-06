package hari.app.finapp.repositories;

import hari.app.finapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by HariHaraKumar on 5/12/2017.
 */

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //public static final String SQL_FIND_USER_BY_EMAIL="Select u from User u where u.mail= :mail";

    //@Query(SQL_FIND_USER_BY_EMAIL)
    User findByEmail(String mail);
}

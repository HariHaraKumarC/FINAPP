package hari.app.finapp.controllers;

import hari.app.finapp.models.User;
import hari.app.finapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by HariHaraKumar on 12/5/2017.
 */

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Register new user
     * @param user
     * @return String
     */
    @RequestMapping(value = "/user/register",method= RequestMethod.POST)
    public String registerUser(@RequestBody User user) throws Exception {
        if(userService.isUserExists(user)){
            throw new Exception("User with mail id "+user.getEmail()+" already exists");
        }else{
            userService.saveUser(user);
        }
        return "User successfully registered";
    }

    /**
     * Update user
     * @param user
     * @return String
     */
    @RequestMapping(value = "/user/update",method= RequestMethod.PUT)
    public String updateUser(@RequestBody User user) throws Exception {
        if(userService.isUserExists(user)){
            userService.updateUser(user);
        }else{
            throw new Exception("User with mail id "+user.getEmail()+" doesn't exists");
        }
        return "User Details successfully updated";
    }

    /**
     * Delete user
     * @param userId
     * @return String
     */
    @RequestMapping(value = "/user/delete",method= RequestMethod.DELETE)
    public String deleteUser(@RequestParam("userId") long userId) throws Exception {
        if(userService.findUserById(userId) !=null){
            userService.deleteUserById(userId);
        }else{
            throw new Exception("User doesn't exists");
        }
        return "User successfully de-registered";
    }

    /**
     * Fetch user details
     * @param userId
     * @return User
     */
    @RequestMapping(value = "/user/details",method= RequestMethod.DELETE)
    public User getUser(@RequestParam("userId") long userId) throws Exception {
        User user=userService.findUserById(userId);
        if(user==null){
            throw new Exception("User doesn't exists");
        }
        return user;
    }
}

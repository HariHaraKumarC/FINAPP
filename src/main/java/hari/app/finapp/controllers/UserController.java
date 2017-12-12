package hari.app.finapp.controllers;

import hari.app.finapp.models.Portfolio;
import hari.app.finapp.models.User;
import hari.app.finapp.services.UserService;
import hari.app.finapp.utils.FinappCustomResponseMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @return ResponseEntity
     */
    @RequestMapping(value = "/user/register",method= RequestMethod.POST)
    public ResponseEntity<FinappCustomResponseMessageHandler> registerUser(@RequestBody User user) {
        if(userService.isUserExists(user)){
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("User with mail id "+user.getEmail()+" already exists"), HttpStatus.CONFLICT);
        }else{
            userService.saveUser(user);
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("User successfully registered"), HttpStatus.OK);
        }
    }

    /**
     * Update user
     * @param user
     * @return ResponseEntity
     */
    @RequestMapping(value = "/user/update",method= RequestMethod.PUT)
    public ResponseEntity<FinappCustomResponseMessageHandler> updateUser(@RequestBody User user){
        if(userService.isUserExists(user)){
            userService.updateUser(user);
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("User Details successfully updated"), HttpStatus.OK);
        }else{
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("User with mail id "+user.getEmail()+" doesn't exists"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete user
     * @param userId
     * @return ResponseEntity
     */
    @RequestMapping(value = "/user/delete",method= RequestMethod.DELETE)
    public ResponseEntity<FinappCustomResponseMessageHandler> deleteUser(@RequestParam("userId") long userId){
        if(userService.findUserById(userId) !=null){
            userService.deleteUserById(userId);
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("User successfully de-registered"), HttpStatus.OK);
        }else{
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("User doesn't exists"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Fetch user details
     * @param userId
     * @return ResponseEntity
     */
    @RequestMapping(value = "/user/details",method= RequestMethod.GET)
    public ResponseEntity<?> getUser(@RequestParam("userId") long userId){
        User user=userService.findUserById(userId);
        if(user==null){
            return new ResponseEntity<FinappCustomResponseMessageHandler>(new FinappCustomResponseMessageHandler("User doesn't exists"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    /**
     * Fetch all users
     * @return ResponseEntity
     */
    @RequestMapping(value = "/user/listAllUsers",method= RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUser(){
        List<User> users=userService.listAllUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
}

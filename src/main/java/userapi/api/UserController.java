package userapi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import userapi.domain.User;
import userapi.model.UserCreate;
import userapi.model.UserList;
import userapi.model.UserEdit;
import userapi.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
@EnableSwagger2
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // List all users
    @RequestMapping(path = "/users", method = RequestMethod.GET, produces = "application/json")
    public List<UserList> list(){
        List<User> userList = userService.getUsersList();
        List<UserList> userDetailList = new ArrayList<UserList>();

        for(User user : userList){
            userDetailList.add(new UserList(user));
        }

        return userDetailList;
    }

    // Return one user by their id for editing and deleting.
    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET, produces = "application/json")
    public UserList retrieve(@PathVariable("id") Long id){
        User user =  userService.getOne(id);

        if(user != null) {
            return new UserList(user);
        } else {
            throw new NoSuchElementException("User Not Found");
        }
    }

    // Creating a new user.
    @RequestMapping(path = "/users", method = RequestMethod.POST, consumes = "application/json")
    public void save(@RequestBody UserCreate userCreate){
        User user = userCreate.map(new User());
        userService.saveUser(user);
    }

    // Editing an existing user.
    @RequestMapping(path = "/users/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public void update(@PathVariable("id") Long id, @RequestBody UserEdit userUpdate){
        User user =  userService.getOne(id);

        if(user != null){
            user = userUpdate.map(user);
            userService.editUser(user);
        } else {
            throw new NoSuchElementException("User Not Found");
        }
    }

    //Delete a existing user
    @RequestMapping(path = "/users/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id){
        if(userService.existsById(id)){
            userService.deleteUser(id);
        } else {
            throw new NoSuchElementException("User Not Found");
        }
    }
}

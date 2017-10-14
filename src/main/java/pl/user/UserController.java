package pl.user;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.other.Views;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @JsonView(Views.Public.class)
    @GetMapping("/")
    public User getLoggedUser(){
        User user = userService.getUser();
        return user;
    }

    @PutMapping("/edit/user")
    public void editUser(@RequestBody User user){
        userService.editUser(user);
    }

    @PutMapping("/edit/username")
    public void editUsername(@RequestParam String username){
        userService.editUsername(username);
    }

    @PutMapping("/edit/mail")
    public void editMail(@RequestParam String mail){
        userService.editMail(mail);
    }


}

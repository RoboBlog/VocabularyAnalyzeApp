package pl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.security.CustomUserDetailsService;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
public class UserController {

    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;
    @Autowired
    public UserController(UserService userService, CustomUserDetailsService customUserDetailsService) {
        this.userService = userService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping("/user")
    public User getLoggedUser(){
        User user = userService.getUser();
        return user;
    }

    @PutMapping("/edit/username")
    public void editUsername(@RequestParam String username){
        userService.editUsername(username);
    }

    @PutMapping("/edit/mail")
    public void editMail(@RequestParam String mail){
        userService.editMail(mail);
    }

    @GetMapping("/get/role")
    public void getRole(){
        String username = userService.getUsername();
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        System.out.println(userDetails.getAuthorities());

    }

}

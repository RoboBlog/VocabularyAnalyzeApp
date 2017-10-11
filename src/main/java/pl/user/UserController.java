package pl.user;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.other.Views;
//import pl.security.CustomUserDetailsService;


@CrossOrigin(origins = "http://localhost:8000")
@RequestMapping("/api/user/")
@RestController
public class UserController {

    private final UserService userService;

    //    private final CustomUserDetailsService customUserDetailsService;
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

    @PutMapping("/edit/username")
    public void editUsername(@RequestParam String username){
        userService.editUsername(username);
    }

    @PutMapping("/edit/mail")
    public void editMail(@RequestParam String mail){
        userService.editMail(mail);
    }

//    @GetMapping("/get/role")
//    public void getRole(){
//        String username = userService.getUsername();
//        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
//        System.out.println(userDetails.getAuthorities());
//
//    }

}

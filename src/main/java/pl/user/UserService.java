package pl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by maciek on 8/17/17.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUsername(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username;
    }

    public User getUser(){
        String username = getUsername();
        User user = userRepository.findByUsername(username);
        return user;
    }

    public void editUsername(String username) {
        User user = getUser();
        user.setUsername(username);
        userRepository.save(user);
    }

    public void editMail(String mail) {
        User user = getUser();
        user.setEmail(mail);
        userRepository.save(user);
    }
}

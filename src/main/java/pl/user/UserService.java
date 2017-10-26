package pl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

    public void editUser(User user) {
        User userToSave = getUser();
        userToSave.setEmail(user.getEmail());
        userRepository.save(userToSave);
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

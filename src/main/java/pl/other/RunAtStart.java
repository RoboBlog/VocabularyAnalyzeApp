package pl.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.user.User;
import pl.user.UserRepository;

import javax.annotation.PostConstruct;

/**
 * Created by maciek on 8/17/17.
 */
@Component
public class RunAtStart {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public RunAtStart(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void runAtStart(){
        User user = new User("maciek", passwordEncoder.encode("test"), "robovlogg@gmail.com",1);
        userRepository.save(user);
    }
}

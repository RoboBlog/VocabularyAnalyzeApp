package pl.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.security.Authority;
import pl.security.AuthorityRepository;

import javax.validation.Valid;
import java.util.Date;

import static pl.security.AuthorityName.ROLE_USER;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class SignUpController {

    private final Logger logger = LoggerFactory.getLogger(SignUpController.class);
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Autowired
    public SignUpController(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }


    //TODO I should move it to service? Switch post to put?
    @PostMapping("/signup")
    public void signUp(@RequestBody @Valid User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setLastPasswordResetDate(new Date());
        Authority byName = authorityRepository.findByName(ROLE_USER);
        user.setEnabled(true);
        user.addAuthority(byName);

        userRepository.save(user);
        byName.addUser(user);
        authorityRepository.save(byName);
        logger.info("New user: {} is register!", user.getUsername());
    }
}

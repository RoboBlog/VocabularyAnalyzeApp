package pl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountActivationService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Autowired
    public AccountActivationService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public String getUrl(User user){
        String url = "http://localhost:8080/api/email/active/" + user.getUsername() + "/" + user.getActivationCode();
        return url;
    }

    public void sendActivationMail(User user){
        String url = getUrl(user);
        emailService.sendSimpleMessage(user.getEmail(), "Active account mail in VocabularyApp", "Click to active account: " + url);
    }

    public String accountActivation(String username, String activationCode){
        User user = userRepository.findByUsername(username);
        if (Objects.equals(user.getActivationCode(), activationCode) && !user.getEnabled()) {
            user.setEnabled(true);
            userRepository.save(user);
            return "OK";
        } else if (user.getEnabled()) {
            return "Your account is active";
        }
        else{
            return "error";
        }
    }
}
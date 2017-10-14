package pl.user.dictionary;

import org.springframework.stereotype.Service;
import pl.user.User;
import pl.user.UserRepository;
import pl.user.UserService;

@Service
public class LearnService {
    private final UserService userService;
    private final UserRepository userRepository;

    public LearnService(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public void decrementationAmountWords(){
        User user = userService.getUser();
        user.setAmountWords(user.getAmountWords()-1);
        userRepository.save(user);
    }

    public void decrementationAmountWords(int amount){
        User user = userService.getUser();
        user.setAmountWords(user.getAmountWords()-amount);
        userRepository.save(user);
    }

    public void incrementationAmountWords(){
        User user = userService.getUser();
        user.setAmountWords(user.getAmountWords()+1);
        userRepository.save(user);
    }

    public void decrementationAmountKnownWords(){
        User user = userService.getUser();
        user.setAmountKnownWords(user.getAmountKnownWords()-1);
        userRepository.save(user);
    }

    public void decrementationAmountKnownWords(int amount){
        User user = userService.getUser();
        user.setAmountKnownWords(user.getAmountKnownWords()-amount);
        userRepository.save(user);
    }

    public void incrementationAmountKnownWords(){
        User user = userService.getUser();
        user.setAmountKnownWords(user.getAmountKnownWords()+1);
        userRepository.save(user);
    }
}

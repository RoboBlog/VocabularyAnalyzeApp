package pl.user;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    private final UserRepository userRepository;
    private final UserService userService;

    public ScoreService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    public void addOneScore() {
        User user = userService.getUser();
        user.setDayScore(user.getDayScore() + 1);
        user.setScore(user.getScore() + 1);
        userRepository.save(user);

    }

    //hmmm, it is good place?
    @Scheduled(cron = "0 0 12 * * ?")
    public void resetAllDayScore() {
        userRepository.resetAllDayScore();
    }


    //TODO IMPLEMENTATION THIS with pagination
    //save to database top 100?
    public void getAllTopScores() {

    }

    public void getAllDayTopScores() {

    }
}

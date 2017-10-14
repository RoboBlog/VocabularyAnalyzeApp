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
        user.setScoreV2(user.getScoreV2() + 1); //for futher data analysis
        userRepository.save(user);

    }

    public void subtractOneScoreV2(){
        User user = userService.getUser();
        user.setScoreV2(user.getScoreV2() - 1);
        userRepository.save(user);
    }



    //TODO IMPLEMENTATION THIS with pagination
    //save to database top 100?
    public void getAllTopScores() {

    }

    public void getAllDayTopScores() {

    }
}

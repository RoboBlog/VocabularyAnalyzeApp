package pl.user;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ResetDayScores {

    private final UserRepository userRepository;

    public ResetDayScores(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Scheduled(cron = "0 0 12 * * ?")
    public void resetAllDayScore() {
        userRepository.resetAllDayScore();
    }
}

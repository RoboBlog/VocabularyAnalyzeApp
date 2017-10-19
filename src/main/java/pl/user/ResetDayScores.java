package pl.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ResetDayScores {
    private static Logger logger = LoggerFactory.getLogger(ResetDayScores.class);

    private final UserRepository userRepository;

    public ResetDayScores(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Scheduled(cron = "${scheduler.reset-day-score}")
    public void resetAllDayScore() {
        userRepository.resetAllDayScore();
        logger.info("Reset all day score");
    }
}

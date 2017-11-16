package pl.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import pl.dictionary.UserDictionariesService;
import pl.dictionary.UserDictionary;
import pl.dictionary.UserWord;
import pl.other.QuizIsDoneException;
import pl.translator.Word;
import pl.user.ScoreService;
import pl.user.User;
import pl.user.UserService;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class QuizService {
    private final UserDictionariesService userDictionariesService;
    private final UserService userService;
    private final QuizRepository quizRepository;
    private final ExerciseRepository exerciseRepository;
    private final ScoreService scoreService;

    @Autowired
    public QuizService(UserDictionariesService userDictionariesService, UserService userService, QuizRepository quizRepository, ExerciseRepository exerciseRepository, ScoreService scoreService) {
        this.userDictionariesService = userDictionariesService;
        this.userService = userService;
        this.quizRepository = quizRepository;
        this.exerciseRepository = exerciseRepository;
        this.scoreService = scoreService;
    }

    public List<Quiz> getAllQuizes(){
        User user = userService.getUser();
        List<Quiz> quizes = user.getQuizes();
        return quizes;
    }



    public Quiz createNewQuiz(long dictionaryId){
        User user = userService.getUser();

        UserDictionary dictionary = userDictionariesService.getDictionary(dictionaryId);
        Set<UserWord> words = dictionary.getWords();

        Quiz quiz = new Quiz();
        quiz.setUser(user);

        words.forEach(word -> {
            Exercise exercise = new Exercise(word);
            quiz.addExercise(exercise);
        });

        quizRepository.save(quiz);
        return quiz;
    }


    public Exercise getExercise(long quizId) {
        User user = userService.getUser();
        Quiz quiz = getQuiz(quizId);

        if (!(quiz.getUser().equals(user))) {
            throw new AccessDeniedException("Access denied!");
        }

        List<Exercise> exercises = quiz.getExercises();
        long sizeAfterFilter = getSizeAfterFilter(exercises);

        if (sizeAfterFilter > 0) {
            Exercise exercise = exercises
                    .stream()
                    .filter(exercise1 -> !exercise1.isCorrect())
                    .skip(ThreadLocalRandom.current().nextLong(sizeAfterFilter))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("No such Exercise!"));

            return exercise;
        } else {
            quiz.setDone(true);
            quizRepository.save(quiz);
            //TODO LOGGER AND HANDLER EXCEPTION
            throw new QuizIsDoneException("Quiz is done!");
        }
    }

    //TODO SECURITY & refactor & add return dto
    public String checkAnswer(long exerciseId, String answer, String type) {
        String response = "is Correct";
        Exercise exercise = exerciseRepository.findExerciseById(exerciseId);
        System.out.println(exercise);
        exercise.setAnswer(answer);
        UserWord userWord = exercise.getUserWord();
        Word word = exercise.getUserWord().getWord();

        if (Objects.equals(type, "pe")) {
            if (Objects.equals(word.getEnglishWord(), answer)) {
                exercise.setCorrect(true);
                userWord.setCorrectness(userWord.getCorrectness() + 1);
                scoreService.addOneScore();
            } else {
                exercise.setCorrect(false);
                userWord.setCorrectness(userWord.getCorrectness() - 1);
                scoreService.subtractOneScoreV2();
                response = "is Incorrect";
            }
        } else if (Objects.equals(type, "ep")) {
            if (Objects.equals(word.getPolishWord(), answer)) {
                exercise.setCorrect(true);
                userWord.setCorrectness(userWord.getCorrectness() + 1);
                scoreService.addOneScore();
            } else {
                exercise.setCorrect(false);
                userWord.setCorrectness(userWord.getCorrectness() - 1);
                scoreService.subtractOneScoreV2();
                response = "is Incorrect";
            }
        }

        return response;
    }

    //LOGGER
    public Quiz getQuiz(long quizId) {
        User user = userService.getUser();

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new NoSuchElementException("Not found quiz!"));

        if (!(quiz.getUser().equals(user))) {
            throw new AccessDeniedException("Access denied!");
        }

        return quiz;
    }



    private long getSizeAfterFilter(List<Exercise> exercises) {

        return exercises.stream()
                .filter(exercise1 -> !exercise1.isCorrect())
                .filter(exercise1 -> !exercise1.getUserWord().isKnow())
                .count();
    }

}

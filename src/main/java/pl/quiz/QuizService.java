package pl.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.translator.Word;
import pl.user.ScoreService;
import pl.user.User;
import pl.user.UserRepository;
import pl.user.UserService;
import pl.user.dictionary.UserDictionariesService;
import pl.user.dictionary.UserDictionary;
import pl.user.dictionary.UserDictionaryRepository;
import pl.user.dictionary.UserWord;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class QuizService {
    private final UserDictionariesService userDictionariesService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserDictionaryRepository userDictionaryRepository;
    private final QuizRepository quizRepository;
    private final ExerciseRepository exerciseRepository;
    private final ScoreService scoreService;

    @Autowired
    public QuizService(UserDictionariesService userDictionariesService, UserService userService, UserRepository userRepository, UserDictionaryRepository userDictionaryRepository, QuizRepository quizRepository, ExerciseRepository exerciseRepository, ScoreService scoreService) {
        this.userDictionariesService = userDictionariesService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.userDictionaryRepository = userDictionaryRepository;
        this.quizRepository = quizRepository;
        this.exerciseRepository = exerciseRepository;
        this.scoreService = scoreService;
    }

    public List<Quiz> getAllQuizes(){
        User user = userService.getUser();
        List<Quiz> quizes = user.getQuizes();
        return quizes;
    }


    //TODO SECURITY
    public Quiz createNewQuiz(long dictionaryId){
//        User user = userService.getUser();

        UserDictionary dictionary = userDictionariesService.getDictionary(dictionaryId);
        Set<UserWord> words = dictionary.getWords();

        Quiz quiz = new Quiz();

        words.forEach(word -> {
            Exercise exercise = new Exercise(word);
            quiz.addExercise(exercise);
        });

        quizRepository.save(quiz); //it is necessary?
//        userRepository.save(user);
        return quiz;
    }


    //TODO SECURITY
    public Exercise getExercise(long quizId) {
        Quiz quiz = getQuiz(quizId);
        List<Exercise> exercises = quiz.getExercises();
        long sizeAfterFilter = getSizeAfterFilter(exercises);

        if (sizeAfterFilter > 0) {
            Optional<Exercise> exercise = exercises
                    .stream()
                    .filter(exercise1 -> !exercise1.isCorrect())
                    .skip(ThreadLocalRandom.current().nextLong(sizeAfterFilter))
                    .findFirst();

            return exercise.get();
        } else {
            quiz.setDone(true);
        // TODO quizRepository.save(quiz); //it is necessary?
            System.out.println("Quiz is done");
            return new Exercise();
        }
    }

    //TODO SECURITY & refactor & add return dto
    public String checkAnswer(long exerciseId, String answer, String type) {
        String response = "is Correct";
        Exercise exercise = exerciseRepository.findExerciseById(exerciseId);
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


    //    TODO SECURITY && OPTIONAL
    public Quiz getQuiz(long quizId) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        return quiz.get();
    }


    private long getSizeAfterFilter(List<Exercise> exercises) {

        return exercises.stream()
                .filter(exercise1 -> !exercise1.isCorrect())
                .count();
    }

}

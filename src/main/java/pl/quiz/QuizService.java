package pl.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import pl.dictionary.UserDictionariesService;
import pl.dictionary.UserDictionary;
import pl.dictionary.UserWord;
import pl.translator.Word;
import pl.user.ScoreService;
import pl.user.User;
import pl.user.UserService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
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

        if(user == quiz.getUser()) {
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
                quizRepository.save(quiz);
                //TODO log quiz is done
                return new Exercise();
//                throw new QuizIsDone()//add exception
            }
        }
        else{
            throw new AccessDeniedException("Access Denied!");
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


    //    TODO OPTIONAL, TEST IT !!!
    public Quiz getQuiz(long quizId) throws IllegalArgumentException {
        User user = userService.getUser();

        Optional<Quiz> quiz = quizRepository.findById(quizId);

        return quiz.map(q->{
           if(q.getUser()!=user)
               throw new AccessDeniedException("Access denied!");
           else
               return q;
        }).orElseThrow(() -> {
            throw new NotFoundException("Not found quiz!");
        });

//        return quiz.flatMap(quiz1 -> ? quiz1 -> quiz)
//                    .flatMap()(q->{
//            return q
//        })
//        quiz.ifPresent(q ->{
//            if(q.getUser()==user){
//                return q;
//            }
//            else{
//                throw new AccessDeniedException("Access Denied!");
//            }
//        });
//        quiz.orElseThrow(throw new IllegalAccessError("Quiz not found"))//notFoundExc TODO

//        return quiz.flatMap(Quiz::getUser)
//                .orElseThrow(new AccessDeniedException("Access Denied!"));

//        if(user == quiz.get().getUser()) {
//            return quiz.get();
//        }
//        else{
//            throw new AccessDeniedException("Access Denied!");
//        }
    }


    private long getSizeAfterFilter(List<Exercise> exercises) {

        return exercises.stream()
                .filter(exercise1 -> !exercise1.isCorrect())
                .filter(exercise1 -> !exercise1.getUserWord().isKnow())
                .count();
    }

}

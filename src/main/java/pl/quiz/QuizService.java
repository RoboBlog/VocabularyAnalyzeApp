package pl.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.user.User;
import pl.user.UserRepository;
import pl.user.UserService;
import pl.user.dictionary.ExerciseWord;
import pl.user.dictionary.UserDictionariesService;
import pl.user.dictionary.UserDictionary;
import pl.user.dictionary.UserWord;

import java.util.List;

@Service
public class QuizService {
    private final UserDictionariesService userDictionariesService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(UserDictionariesService userDictionariesService, UserService userService, UserRepository userRepository, QuizRepository quizRepository) {
        this.userDictionariesService = userDictionariesService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
    }

    public List<Quiz> getAllQuizes(){
        User user = userService.getUser();
        List<Quiz> quizes = user.getQuizes();
        return quizes;
    }

    //TODO SECURITY
    public Quiz createNewQuiz(long dictionaryId){
        UserDictionary dictionary = userDictionariesService.getDictionary(dictionaryId);
        User user = userService.getUser();
        Quiz quiz = new Quiz();

//        quiz.addExercise();
//        user.addQuiz(quiz);

        quizRepository.save(quiz); //it is necessary?
        userRepository.save(user);
        return quiz;
    }

//    TODO SECURITY
//    public UserWord getExercise(long dictionaryId){
//        UserDictionary dictionary = userDictionariesService.getDictionary(dictionaryId);
//        List<ExerciseWord> words = dictionary.getWords();
//    }
}

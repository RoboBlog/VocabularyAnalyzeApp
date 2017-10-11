package pl.other;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.quiz.Exercise;
import pl.quiz.ExerciseRepository;
import pl.quiz.Quiz;
import pl.quiz.QuizService;
import pl.translator.Word;
import pl.translator.WordRepository;
import pl.user.ScoreService;
import pl.user.dictionary.UserDictionary;
import pl.user.dictionary.UserDictionaryRepository;
import pl.user.dictionary.UserWord;
import pl.user.dictionary.UserWordRepository;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/")
public class TestController {

    private final UserWordRepository userWordRepository;
    private final WordRepository wordRepository;
    private final ExerciseRepository exerciseRepository;
    private final UserDictionaryRepository userDictionaryRepository;
    private final QuizService quizService;
    private final ScoreService scoreService;

    public TestController(UserWordRepository userWordRepository, WordRepository wordRepository, ExerciseRepository exerciseRepository, UserDictionaryRepository userDictionaryRepository, QuizService quizService, ScoreService scoreService) {
        this.userWordRepository = userWordRepository;
        this.wordRepository = wordRepository;
        this.exerciseRepository = exerciseRepository;
        this.userDictionaryRepository = userDictionaryRepository;
        this.quizService = quizService;
        this.scoreService = scoreService;
    }

//    @GetMapping("/add")
//    public void add(){
//        scoreService.addOneScore();
//    }
//
//
//    @GetMapping("/reset")
//    public void reset() {
//        scoreService.resetAllDayScore();
//    }

    @GetMapping
    public void get() {
//       Word word1 = new Word("test1", "test1");
//       Word word2 = new Word("test2", "test2");
//       Word word3 = new Word("test3", "test3");
//       Word word4 = new Word("test4", "test4");
//       Word word5 = new Word("test5", "test5");
//       Word word6 = new Word("test6", "test6");
//       Word word7 = new Word("test7", "test7");
//       Word word8 = new Word("test8", "test8");
//       Word word9 = new Word("test9", "test9");
//
//        wordRepository.save(word1);
//        wordRepository.save(word2);
//        wordRepository.save(word3);
//        wordRepository.save(word4);
//        wordRepository.save(word5);
//        wordRepository.save(word6);
//        wordRepository.save(word7);
//        wordRepository.save(word8);
//        wordRepository.save(word9);
        Set<UserWord> userWords = new HashSet<>();


        for (int i = 0; i < 10; i++) {
            Word word = new Word("test" + i, "test1");
//            wordRepository.save(word);

            UserWord userWord = new UserWord(word);
            userWordRepository.save(userWord);

            userWords.add(userWord);
            System.out.println(i);

        }
//        UserWord userWord1 = new UserWord(word1);
//        UserWord userWord2 = new UserWord(word2);
//        UserWord userWord3 = new UserWord(word3);
//        UserWord userWord4 = new UserWord(word4);
//        UserWord userWord5 = new UserWord(word5);
//        UserWord userWord6 = new UserWord(word6);
//        UserWord userWord7 = new UserWord(word7);
//        UserWord userWord8 = new UserWord(word8);
//        UserWord userWord9 = new UserWord(word9);
//
//        userWordRepository.save(userWord1);
//        userWordRepository.save(userWord2);
//        userWordRepository.save(userWord3);
//        userWordRepository.save(userWord4);
//        userWordRepository.save(userWord5);
//        userWordRepository.save(userWord6);
//        userWordRepository.save(userWord7);
//        userWordRepository.save(userWord8);
//        userWordRepository.save(userWord9);

//        UserWord byId1 = userWordRepository.findById(userWord1.getId());
//        UserWord byId2 = userWordRepository.findById(userWord2.getId());
//        UserWord byId3 = userWordRepository.findById(userWord3.getId());
//        UserWord byId4 = userWordRepository.findById(userWord4.getId());
//        UserWord byId5 = userWordRepository.findById(userWord5.getId());
//        UserWord byId6 = userWordRepository.findById(userWord6.getId());
//        UserWord byId7 = userWordRepository.findById(userWord7.getId());
//        UserWord byId8 = userWordRepository.findById(userWord8.getId());
//        UserWord byId9 = userWordRepository.findById(userWord9.getId());

//
//        Exercise exercise1 = new Exercise(byId1);
//        Exercise exercise2 = new Exercise(byId2);
//        Exercise exercise3 = new Exercise(byId3);
//        Exercise exercise4 = new Exercise(byId4);
//        Exercise exercise5 = new Exercise(byId5);
//        Exercise exercise6 = new Exercise(byId6);
//        Exercise exercise7 = new Exercise(byId7);
//        Exercise exercise8 = new Exercise(byId8);
//        Exercise exercise9 = new Exercise(byId9);
//        exerciseRepository.save(exercise1);
//        exerciseRepository.save(exercise2);
//        exerciseRepository.save(exercise3);
//        exerciseRepository.save(exercise4);
//        exerciseRepository.save(exercise5);
//        exerciseRepository.save(exercise6);
//        exerciseRepository.save(exercise7);
//        exerciseRepository.save(exercise8);
//        exerciseRepository.save(exercise9);
//
//        List<UserWord> userWords = new LinkedList<>();
//        userWords.add(byId1);
//        userWords.add(byId2);
//        userWords.add(byId3);
//        userWords.add(byId4);
//        userWords.add(byId5);
//        userWords.add(byId6);
//        userWords.add(byId7);
//        userWords.add(byId8);
//        userWords.add(byId9);


        UserDictionary userDictionary = new UserDictionary("quiz1");
        userDictionary.setWords(userWords);
        userDictionaryRepository.save(userDictionary);
        System.out.println(userDictionary.getId());

    }

    @GetMapping("/role")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String testRole() {
        return "TEST IS COMPLETE";
    }

    @GetMapping("/{dictionaryId}")
    public Quiz createNewQuiz(@PathVariable long dictionaryId) {
        Quiz newQuiz = quizService.createNewQuiz(dictionaryId);
        return newQuiz;
    }


    @GetMapping("/quiz/{quizId}")
    public Exercise getExercise(@PathVariable long quizId) {
        Exercise exercise = quizService.getExercise(quizId);
        return exercise;
    }

}

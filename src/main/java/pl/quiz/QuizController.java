package pl.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.user.dictionary.UserWord;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/quiz/")
public class QuizController {
//    private final QuizService quizService;
//
//    @Autowired
//    public QuizController(QuizService quizService) {
//        this.quizService = quizService;
//    }
//
//    @GetMapping("/all")
//    public List<Quiz> getAllQuizes(){
//        List<Quiz> allQuizes = quizService.getAllQuizes();
//        return allQuizes;
//    }
//
////    @GetMapping("/{quizId}/exercise")
////    public UserWord getExercise(){
////        UserWord exercise = quizService.getExercise();
////        return exercise;
//    }
//    @PostMapping()
//    public
//
//    @PostMapping("/{dictionaryId}")
//    public Quiz createNewQuiz(@PathVariable long dictionaryId){
//        Quiz newQuiz = quizService.createNewQuiz(dictionaryId);
//        return newQuiz;
//    }
//
////    public getExercise(){
////
////    }
//
//    @GetMapping("/{dictionaryId}/")
//    public int quiz(){
////        Quiz quiz = new Quiz();
//        Random r = new Random();
//        int max=10;
//        int min=2;
////        int wordNumber = r.nextInt((max - min)+1) + min;
//        int wordNumber = r.nextInt(max+1) ;
//        return wordNumber;
////        Exercise exercise = new Exercise()
////        return "quiz";
//    }
}
